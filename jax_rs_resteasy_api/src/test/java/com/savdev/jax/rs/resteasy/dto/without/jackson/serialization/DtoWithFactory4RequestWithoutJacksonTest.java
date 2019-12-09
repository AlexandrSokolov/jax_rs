package com.savdev.jax.rs.resteasy.dto.without.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoWithFactory4RequestWithoutJacksonTest {


  /**
   * Convert object to String and compare with file content from the file
   *
   * This check is usefull for requests, when we need to make sure, that correct values will be sent to server
   *
   * @throws Exception
   */
  @Test
  public void testFromDto2String4Requests() throws Exception {
    //we customize object mapper:
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    Assert.assertEquals(
      readTestFile(JSON_FILE, DtoWithFactory4RequestWithoutJacksonTest.class),
      RequestResponseInfo.stringify(objectMapper, instance()));
  }

  private DtoWithFactory4RequestWithoutJackson instance() throws ParseException {
    return DtoWithFactory4RequestWithoutJackson.instance(
      "test string value",
      100,
      200L,
      100.05F,
      110.05,
      new BigDecimal("220.000777"),
      'B',
      'a',
      DATE_FORMAT.parse("2020-05-10 12:23:54"),
      new java.sql.Date(DATE_FORMAT.parse("2020-07-08 10:03:14").getTime()),
      true,
      false);
  }
}
