package com.savdev.jax.rs.resteasy.client.html_form;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import java.math.BigDecimal;
import java.util.Calendar;

public class HtmlFormService implements HtmlFormApi {

  public static final String UPDATED_PREFIX = "UPDATED ";

  @Override
  public RestDto updateDto(String name, BigDecimal bigDecimal) {
    return RestDto.instance(
      UPDATED_PREFIX + name,
      Calendar.getInstance().getTime(),
      bigDecimal);
  }

}
