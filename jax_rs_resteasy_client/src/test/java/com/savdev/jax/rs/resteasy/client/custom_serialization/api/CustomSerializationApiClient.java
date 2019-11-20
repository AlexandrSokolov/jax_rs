package com.savdev.jax.rs.resteasy.client.custom_serialization.api;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.custom_serialization.api.CustomSerializationApiServer.CUSTOM_SERIALIZATION_PATH;

@Path(CUSTOM_SERIALIZATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface CustomSerializationApiClient {

  @GET
  RestDto getDto();

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  RestDto updateDto(RestDto dto);
}
