package com.savdev.jax.rs.resteasy.client.custom_object_mapping.service;

import com.savdev.jax.rs.resteasy.client.custom_object_mapping.api.CustomOmUnknownPropertiesApiServer;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.dto.RestDtoExtraProps;

import java.math.BigDecimal;
import java.util.Calendar;

public class CustomOmUnknownPropertiesService implements CustomOmUnknownPropertiesApiServer {

  public static final RestDtoExtraProps CO_INSTANCE_WITH_UNKNOWN_FIELDS = RestDtoExtraProps.instance(
    "Custom Object Mapper Test Name",
    Calendar.getInstance().getTime(),
    new BigDecimal("322.22"),
    7878L);

  public static final RestDto CO_INSTANCE_WITH_FEWER_FIELDS = RestDto.instance(
    "COM fewer fields name",
    Calendar.getInstance().getTime(),
    new BigDecimal("322.44"));

  @Override
  public RestDtoExtraProps getDtoWithExtraFields() {
    return CO_INSTANCE_WITH_UNKNOWN_FIELDS;
  }

  @Override
  public RestDto getDtoWithFewerFieldsNumberThanExpected() {
    return CO_INSTANCE_WITH_FEWER_FIELDS;
  }
}
