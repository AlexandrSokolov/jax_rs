package com.savdev.jax.rs.resteasy.client.custom_json.api;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiServer.WRONG_CUSTOM_JSON_API_PATH;
import static com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiServer.WRONG_CUSTOM_JSON_MEDIA_TYPE;


@Path(WRONG_CUSTOM_JSON_API_PATH)
@Produces(WRONG_CUSTOM_JSON_MEDIA_TYPE)
public interface WrongCustomJsonApiClient {

  @GET
  RestDto getDto();

  @PUT
  @Consumes(WRONG_CUSTOM_JSON_MEDIA_TYPE)
  RestDto updateDto(RestDto dto);
}
