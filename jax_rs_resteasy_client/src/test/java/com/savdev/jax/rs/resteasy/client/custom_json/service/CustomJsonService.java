package com.savdev.jax.rs.resteasy.client.custom_json.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savdev.jax.rs.resteasy.client.custom_json.api.CustomJsonApiServer;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Calendar;

public class CustomJsonService implements CustomJsonApiServer {

  public static final RestDto INSTANCE_TO_UPDATE = RestDto.instance(
    "fewer fields name",
    Calendar.getInstance().getTime(),
    new BigDecimal("322.44"));

  public static final String UPDATE_PREFIX = "changed: ";

  @Override
  public Response getDto() {
    return Response.ok()
      .type(CUSTOM_JSON_MEDIA_TYPE)
      .entity(RequestResponseInfo.stringify(new ObjectMapper(), INSTANCE_TO_UPDATE))
      .build();
  }

  @Override
  public Response updateDto(RestDto dto) {
    return Response.ok()
      .type(CUSTOM_JSON_MEDIA_TYPE)
      .entity(RequestResponseInfo.stringify(
        new ObjectMapper(),
        RestDto.instance(
          UPDATE_PREFIX + dto.getName(),
          dto.getDate(),
          dto.getBigDecimal())))
      .build();
  }
}
