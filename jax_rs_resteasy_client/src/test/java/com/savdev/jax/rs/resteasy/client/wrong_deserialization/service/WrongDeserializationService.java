package com.savdev.jax.rs.resteasy.client.wrong_deserialization.service;

import com.savdev.jax.rs.resteasy.client.wrong_deserialization.api.WrongDeserializationApiServer;

import javax.ws.rs.core.Response;

public class WrongDeserializationService implements WrongDeserializationApiServer {

  public static final String WRONG_JSON = "[ \"test\" : 123 ]";

  @Override
  public Response getDto() {
    return Response.ok().entity(WRONG_JSON).build();
  }
}
