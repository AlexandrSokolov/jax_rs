package com.savdev.jax.rs.resteasy.client.custom_json.api;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.savdev.jax.rs.resteasy.client.custom_json.api.CustomJsonApiServer.CUSTOM_JSON_API_PATH;
import static com.savdev.jax.rs.resteasy.client.custom_json.api.CustomJsonApiServer.CUSTOM_JSON_MEDIA_TYPE;


@Path(CUSTOM_JSON_API_PATH)
@Produces(CUSTOM_JSON_MEDIA_TYPE)
public interface CustomJsonApiClient {

  @GET
  RestDto getDto();

  @PUT
  @Consumes(CUSTOM_JSON_MEDIA_TYPE)
  RestDto updateDto(RestDto dto);
}
