package com.savdev.jax.rs.resteasy.client.wrong_deserialization.api;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.wrong_deserialization.api.WrongDeserializationApiServer.WRONG_DESERIALIZATION_API_PATH;

@Path(WRONG_DESERIALIZATION_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface WrongDeserializationApiClient {

  /**
   * Server returns a dto with property, not known to the client.
   * @return
   */
  @GET
  RestDto getDto();
}
