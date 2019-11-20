package com.savdev.jax.rs.resteasy.client.provide_config;

import com.savdev.jax.rs.resteasy.client.ClientRequestFilterFactory;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.provide_config.api.ProvideConfigApiClient;
import com.savdev.jax.rs.resteasy.client.provide_config.api.ProxyConfigProvider;
import com.savdev.jax.rs.resteasy.client.provide_config.service.ProvideConfigApiService;
import com.savdev.jax.rs.resteasy.client.success.api.Api;
import com.savdev.jax.rs.resteasy.client.success.service.ApiService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import static com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig.DOMAIN_NOT_SET;

public class ProvideConfigTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(ProvideConfigApiService.class));
  }

  /**
   * We need a custom deserializer, to convert string from the server response to java.util.Date
   * No deserializer is provided by API, via ProxyConfigProvider class
   */
  @Test
  public void testGet(){
    JaxRsProxyConfig proxyConfig = ProxyConfigProvider.instance()
      .domain(URI.toString())
      .clientRequestFilterSupplier(() ->
        ClientRequestFilterFactory.basicAuthentication("someLogin", "somePwd"));
    RestDto dto = proxyConfig.proxy(ProvideConfigApiClient.class).getDto();
    Assert.assertNotNull(dto);
    Date date = dto.getDate();
    Assert.assertNotNull(date);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Assert.assertEquals(2015, calendar.get(Calendar.YEAR));
    Assert.assertEquals(6, calendar.get(Calendar.MONTH));
    Assert.assertEquals(04, calendar.get(Calendar.DATE));
    //TODO compare hour, minute;
  }

  /**
   * Test put. We need a custom serializer, to conver java.util.Date in a request to a string, server can handle
   * and a custom deserializer, to convert a date from a response into java.util.Date
   */
  @Test
  public void testPut(){
    JaxRsProxyConfig proxyConfig = ProxyConfigProvider.instance()
      .domain(URI.toString())
      .clientRequestFilterSupplier(() ->
        ClientRequestFilterFactory.basicAuthentication("someLogin", "somePwd"));
    RestDto original = RestDto.instance("fake name", Calendar.getInstance().getTime(), null);
    RestDto dto = proxyConfig.proxy(ProvideConfigApiClient.class).updateDto(original);
    Assert.assertNotNull(dto);
    Date date = dto.getDate();
    Assert.assertNotNull(date);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Assert.assertEquals(2017, calendar.get(Calendar.YEAR));
    //2019-10-24, 9 month in java Calendar (starts with 0)
    Assert.assertEquals(10, calendar.get(Calendar.MONTH));
    Assert.assertEquals(1, calendar.get(Calendar.DATE));
    //TODO compare hour, minute;
  }

  @Test
  public void testWithoutDomainSetting(){
    try {
      JaxRsProxyConfig proxyConfig = ProxyConfigProvider.instance();
      proxyConfig.proxy(ProvideConfigApiClient.class).getDto();
      Assert.fail("Error is expected without setting a domain");
    } catch (Exception e){
      Assert.assertEquals(IllegalStateException.class, e.getClass());
      Assert.assertEquals(DOMAIN_NOT_SET, e.getMessage());
    }

  }
}
