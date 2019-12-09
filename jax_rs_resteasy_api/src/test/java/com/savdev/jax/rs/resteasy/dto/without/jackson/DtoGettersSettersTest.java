package com.savdev.jax.rs.resteasy.dto.without.jackson;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;


public class DtoGettersSettersTest {

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
      readTestFile(JSON_FILE, DtoGettersSettersTest.class),
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
        DtoGettersSettersTest.class.getResourceAsStream(JSON_FILE),
        DtoGettersSettersWithoutJackson.class));
  }

  private DtoGettersSettersWithoutJackson instance() throws ParseException {
    DtoGettersSettersWithoutJackson dto = new DtoGettersSettersWithoutJackson();

    dto.setStringValue("test string value");

    dto.setIntValue(100);
    dto.setLongValue(200L);

    dto.setFloatValue(100.05F);
    dto.setDoubleValue(110.05);
    //do not init it as 220.000777, but as a string "220.000777"
    dto.setBigDecimalValue(new BigDecimal("220.000777"));

    dto.setCharPrimitiveValue('a');
    dto.setCharObjectValue('B');

    dto.setJavaDateValue(DATE_FORMAT.parse("2020-05-10 12:23:54"));
    dto.setSqlDateValue(new java.sql.Date(DATE_FORMAT.parse("2020-07-08 10:03:14").getTime()));

    dto.setBooleanPrimitiveValue(true);
    dto.setBooleanObjectValue(false);

    return dto;
  }
}
