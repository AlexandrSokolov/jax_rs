package com.savdev.jax.rs.resteasy.client.custom_json.api;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiServer.WRONG_CUSTOM_JSON_API_PATH;
import static com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiServer.WRONG_CUSTOM_JSON_MEDIA_TYPE;


@Path(WRONG_CUSTOM_JSON_API_PATH)
@Produces(WRONG_CUSTOM_JSON_MEDIA_TYPE)
public interface WrongCustomJsonApiServer {

  String WRONG_CUSTOM_JSON_API_PATH = "/rest/test/wrong/custom/json/path";
  //custom json cannot be specified this way:
  String WRONG_CUSTOM_JSON_MEDIA_TYPE = "application/vnd.custom.v1.json";

  @GET
  Response getDto();

  @PUT
  @Consumes(WRONG_CUSTOM_JSON_MEDIA_TYPE)
  Response updateDto(RestDto dto);
}
