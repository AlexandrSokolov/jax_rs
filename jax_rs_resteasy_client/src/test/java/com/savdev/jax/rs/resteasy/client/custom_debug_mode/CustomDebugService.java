package com.savdev.jax.rs.resteasy.client.custom_debug_mode;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class CustomDebugService implements CustomDebugApi {

  static final String TEST_NAME = "Custom Debug Mode testName";
  static final BigDecimal TEST_BD = new BigDecimal("989.22");
  static final Date TEST_DATE = Calendar.getInstance().getTime();

  static final RestDto CD_INSTANCE = RestDto.instance(TEST_NAME, TEST_DATE, TEST_BD);

  @Override
  public RestDto getDto() {
    return CD_INSTANCE;
  }
}
