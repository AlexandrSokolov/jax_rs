package com.savdev.jax.rs.resteasy.client.unknown_properties.service;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.dto.RestDtoExtraProps;
import com.savdev.jax.rs.resteasy.client.unknown_properties.api.UnknownPropertiesApiServer;

import java.math.BigDecimal;
import java.util.Calendar;

public class UnknownPropertiesService implements UnknownPropertiesApiServer {

  public static final RestDtoExtraProps INSTANCE_WITH_UNKNOWN_FIELDS = RestDtoExtraProps.instance(
    "test name",
    Calendar.getInstance().getTime(),
    new BigDecimal("322.22"),
    7878L);

  public static final RestDto INSTANCE_WITH_FEWER_FIELDS = RestDto.instance(
    "fewer fields name",
    Calendar.getInstance().getTime(),
    new BigDecimal("322.44"));

  @Override
  public RestDtoExtraProps getDtoWithExtraFields() {
    return INSTANCE_WITH_UNKNOWN_FIELDS;
  }

  @Override
  public RestDto getDtoWithFewerFieldsNumberThanExpected() {
    return INSTANCE_WITH_FEWER_FIELDS;
  }
}
