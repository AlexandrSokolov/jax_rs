package com.savdev.jax.rs.resteasy.dto.deserialization;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;

public class DtoConstructor4ResponseTest {

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
        DtoConstructor4ResponseTest.class.getResourceAsStream(JSON_FILE),
        DtoConstructor4ResponseWithJackson.class));
  }

  private DtoConstructor4ResponseWithJackson instance() throws ParseException {
    return new DtoConstructor4ResponseWithJackson(
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
