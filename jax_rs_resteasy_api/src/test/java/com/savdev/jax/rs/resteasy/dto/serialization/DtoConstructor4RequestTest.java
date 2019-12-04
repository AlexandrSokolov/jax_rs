package com.savdev.jax.rs.resteasy.dto.serialization;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoConstructor4RequestTest {


  /**
   * Convert object to String and compare with file content from the file
   *
   * This check is useful for requests, when we need to make sure, that correct values will be sent to server
   *
   * @throws Exception
   */
  @Test
  public void testFromDto2String4Requests() throws Exception {
    Assert.assertEquals(
      readTestFile(JSON_FILE, DtoConstructor4RequestTest.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, instance()));
  }

  private DtoConstructor4RequestWithJackson instance() throws ParseException {
    return new DtoConstructor4RequestWithJackson(
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
