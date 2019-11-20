package com.savdev.jax.rs.resteasy.client.success.service;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.success.api.Api;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class ApiService implements Api {

  public static final String TEST_NAME = "testName";
  public static final BigDecimal TEST_BD = new BigDecimal("125.22");
  public static final Date TEST_DATE = Calendar.getInstance().getTime();

  public static final RestDto INSTANCE = RestDto.instance(TEST_NAME, TEST_DATE, TEST_BD);
  public static final String UPDATED_PREFIX = "UPDATED ";

  @Override
  public RestDto getDto() {
    return INSTANCE;
  }

  @Override
  public RestDto updateDto(RestDto dto) {
    return RestDto.instance(
      UPDATED_PREFIX + dto.getName(),
      dto.getDate(),
      dto.getBigDecimal());
  }

}
