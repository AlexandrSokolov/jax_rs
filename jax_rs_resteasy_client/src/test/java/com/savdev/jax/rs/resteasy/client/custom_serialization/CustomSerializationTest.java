package com.savdev.jax.rs.resteasy.client.custom_serialization;

import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.custom_serialization.api.CustomSerializationApiClient;
import com.savdev.jax.rs.resteasy.client.custom_serialization.service.CustomSerializationService;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ResponseProcessingException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import static com.savdev.jax.rs.resteasy.client.custom_serialization.service.CustomSerializationService.DATE_STRING;

public class CustomSerializationTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(CustomSerializationService.class));
  }

  /**
   * We need a custom deserializer, to convert string from the server response to java.util.Date
   */
  @Test
  public void testGet(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .addDeserializer((dateStr) -> {
        Instant instant = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateStr));
        return java.util.Date.from(instant);
      }, Date.class);
    RestDto dto = proxyConfig.proxy(CustomSerializationApiClient.class).getDto();
    Assert.assertNotNull(dto);
    Date date = dto.getDate();
    Assert.assertNotNull(date);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Assert.assertEquals(2018, calendar.get(Calendar.YEAR));
    Assert.assertEquals(11, calendar.get(Calendar.MONTH));
    Assert.assertEquals(04, calendar.get(Calendar.DATE));
    //TODO compare hour, minute;
  }

  /**
   * Test put. We need a custom serializer, to conver java.util.Date in a request to a string, server can handle
   * and a custom deserializer, to convert a date from a response into java.util.Date
   */
  @Test
  public void testPut(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .addDeserializer((dateStr) -> {
        Instant instant = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateStr));
        return java.util.Date.from(instant);
      }, Date.class)
      .addSerializer( date ->
          DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(
            ZonedDateTime.ofInstant(date.toInstant(), TimeZone.getDefault().toZoneId())), Date.class);
    RestDto original = RestDto.instance("fake name", Calendar.getInstance().getTime(), null);
    RestDto dto = proxyConfig.proxy(CustomSerializationApiClient.class).updateDto(original);
    Assert.assertNotNull(dto);
    Date date = dto.getDate();
    Assert.assertNotNull(date);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Assert.assertEquals(2019, calendar.get(Calendar.YEAR));
    //2019-10-24, 9 month in java Calendar (starts with 0)
    Assert.assertEquals(9, calendar.get(Calendar.MONTH));
    Assert.assertEquals(24, calendar.get(Calendar.DATE));
    //TODO compare hour, minute;
  }

  @Test
  public void testErrorGet(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
      proxyConfig.proxy(CustomSerializationApiClient.class).getDto();
      Assert.fail("Must fail without custom date serializer");
    } catch (Exception e){
      Assert.assertEquals(ResponseProcessingException.class, e.getClass());
      Assert.assertTrue(e.getMessage().contains(DATE_STRING));
    }

  }
}
