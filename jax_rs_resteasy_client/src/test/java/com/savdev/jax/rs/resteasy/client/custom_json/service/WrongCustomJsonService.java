package com.savdev.jax.rs.resteasy.client.custom_json.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiServer;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;

import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.custom_json.service.CustomJsonService.INSTANCE_TO_UPDATE;
import static com.savdev.jax.rs.resteasy.client.custom_json.service.CustomJsonService.UPDATE_PREFIX;

public class WrongCustomJsonService implements WrongCustomJsonApiServer {

  @Override
  public Response getDto() {
    return Response.ok()
      .type(WRONG_CUSTOM_JSON_MEDIA_TYPE)
      .entity(RequestResponseInfo.stringify(new ObjectMapper(), INSTANCE_TO_UPDATE))
      .build();
  }

  @Override
  public Response updateDto(RestDto dto) {
    return Response.ok()
      .type(WRONG_CUSTOM_JSON_MEDIA_TYPE)
      .entity(RequestResponseInfo.stringify(
        new ObjectMapper(),
        RestDto.instance(
          UPDATE_PREFIX + dto.getName(),
          dto.getDate(),
          dto.getBigDecimal())))
      .build();
  }
}
