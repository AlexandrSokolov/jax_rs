package com.savdev.jax.rs.resteasy.dto.deserialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;

public class DtoDefaultConstructor4ResponseTest {

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

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    Assert.assertEquals(
      instance(),
      objectMapper.readValue(
        DtoDefaultConstructor4ResponseTest.class.getResourceAsStream(JSON_FILE),
        DtoDefaultConstructor4ResponseWithoutJackson.class));
  }

  private DtoDefaultConstructor4ResponseWithoutJackson instance() throws ParseException {
    return new DtoDefaultConstructor4ResponseWithoutJackson(
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
