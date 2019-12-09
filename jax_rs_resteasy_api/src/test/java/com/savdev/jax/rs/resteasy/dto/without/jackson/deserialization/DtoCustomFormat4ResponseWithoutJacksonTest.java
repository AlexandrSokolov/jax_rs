package com.savdev.jax.rs.resteasy.dto.without.jackson.deserialization;

import com.savdev.jax.rs.resteasy.dto.without.jackson.DtoCustomFormatWithoutJackson;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.CUSTOM_FORMAT_JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.customizedObjectMapper;

public class DtoCustomFormat4ResponseWithoutJacksonTest {

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
      customizedObjectMapper().readValue(
        DtoCustomFormat4ResponseWithoutJacksonTest.class.getResourceAsStream(CUSTOM_FORMAT_JSON_FILE),
        DtoCustomFormatWithoutJackson.class));
  }

  private DtoCustomFormatWithoutJackson instance() throws ParseException {
    DtoCustomFormatWithoutJackson dto = new DtoCustomFormatWithoutJackson();
    dto.setBigDecimalValue(new BigDecimal(444.25));
    dto.setJavaUtilDateValue(DATE_FORMAT.parse("2015-02-10 11:03:54"));
    return dto;
  }
}
