package com.savdev.jax.rs.resteasy.dto.serialization;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import com.savdev.jax.rs.resteasy.dto.DtoCustomFormatWithJackson;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.CUSTOM_FORMAT_JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoCustomFormat4RequestWithJacksonTest {

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
    Assert.assertEquals(
      readTestFile(CUSTOM_FORMAT_JSON_FILE, DtoCustomFormat4RequestWithJacksonTest.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, instance()));
  }

  private DtoCustomFormatWithJackson instance() throws ParseException {
    DtoCustomFormatWithJackson dto = new DtoCustomFormatWithJackson();
    dto.setBigDecimalValue(new BigDecimal(444.25));
    dto.setJavaUtilDateValue(DATE_FORMAT.parse("2015-02-10 11:03:54"));
    return dto;
  }
}
