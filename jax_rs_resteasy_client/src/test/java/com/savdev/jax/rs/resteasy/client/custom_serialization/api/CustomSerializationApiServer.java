package com.savdev.jax.rs.resteasy.client.custom_serialization.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.custom_serialization.api.CustomSerializationApiServer.CUSTOM_SERIALIZATION_PATH;

@Path(CUSTOM_SERIALIZATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface CustomSerializationApiServer {

  String CUSTOM_SERIALIZATION_PATH = "/custom/serialization";

  @GET
  Response restDto();

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  Response updateDto(String dtoAsString);
}
