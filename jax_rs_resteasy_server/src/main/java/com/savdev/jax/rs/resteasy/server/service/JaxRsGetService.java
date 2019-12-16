package com.savdev.jax.rs.resteasy.server.service;


import com.savdev.jax.rs.resteasy.api.JaxRsGet;
import com.savdev.jax.rs.resteasy.dto.without.jackson.DtoGettersSettersWithoutJackson;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JaxRsGetService implements JaxRsGet {

  static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  @Override
  public DtoGettersSettersWithoutJackson findById(long id) {
    return instance(id);
  }


  private DtoGettersSettersWithoutJackson instance(long id) {
    try {
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
      dto.setJavaDateValue(new SimpleDateFormat(DATE_FORMAT).parse("2020-05-10 12:23:54"));
      dto.setSqlDateValue(new java.sql.Date(new SimpleDateFormat(DATE_FORMAT).parse("2020-07-08 10:03:14").getTime()));

      dto.setBooleanPrimitiveValue(true);
      dto.setBooleanObjectValue(false);

      return dto;
    } catch (ParseException e) {
      throw new IllegalStateException(e);
    }

  }
}
