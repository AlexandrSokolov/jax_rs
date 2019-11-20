package com.savdev.jax.rs.resteasy.client.wrong_deserialization.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.wrong_deserialization.api.WrongDeserializationApiServer.WRONG_DESERIALIZATION_API_PATH;


@Path(WRONG_DESERIALIZATION_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface WrongDeserializationApiServer {

  String WRONG_DESERIALIZATION_API_PATH = "/rest/test/path/wrong/deserialization";

  @GET
  Response getDto();

}
