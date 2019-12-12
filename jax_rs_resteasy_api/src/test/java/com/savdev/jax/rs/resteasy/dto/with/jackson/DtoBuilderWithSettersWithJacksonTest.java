package com.savdev.jax.rs.resteasy.dto.with.jackson;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoBuilderWithSettersWithJacksonTest {

  /**
   * Convert object to String and compare with file content from the file
   *
   * This check is usefull for requests, when we need to make sure, that correct values will be sent to server
   *
   * @throws Exception
   */
  @Test
  public void testFromDto2String4Requests() throws Exception {
    Assert.assertEquals(
      readTestFile(JSON_FILE, DtoBuilderWithSettersWithJackson.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, instance()));
  }

  /**
   * Parse a string from the file using Object Mapper,
   *
   * check the real object is according to what we expect when parse response
   *
   * This check is useful for responds
   *
   * @throws Exception
   */
  @Test
  public void testFromString2Dto4Response() throws Exception {
    Assert.assertEquals(
      instance(),
      OBJECT_MAPPER.readValue(
        DtoBuilderWithSettersWithJacksonTest.class.getResourceAsStream(JSON_FILE),
        DtoBuilderWithSettersWithJackson.class));
  }

  private DtoBuilderWithSettersWithJackson instance() throws ParseException {
    return DtoBuilderWithSettersWithJackson.builder()
      .setStringValue("test string value")
      .setIntValue(100)
      .setLongValue(200L)
      .setFloatValue(100.05F)
      .setDoubleValue(110.05)
      //do not init it as 220.000777, but as a string "220.000777"
      .setBigDecimalValue(new BigDecimal("220.000777"))
      .setCharPrimitiveValue('a')
      .setCharObjectValue('B')
      .setJavaDateValue(DATE_FORMAT.parse("2020-05-10 12:23:54"))
      .setSqlDateValue(new java.sql.Date(DATE_FORMAT.parse("2020-07-08 10:03:14").getTime()))
      .setBooleanPrimitiveValue(true)
      .setBooleanObjectValue(false)
      .build();
  }
}
