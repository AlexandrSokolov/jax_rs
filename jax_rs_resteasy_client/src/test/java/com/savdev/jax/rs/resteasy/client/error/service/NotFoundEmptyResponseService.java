package com.savdev.jax.rs.resteasy.client.error.service;

import com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFound;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path(ApiGetNotFound.API_GET_NOT_FOUND_PATH)
public class NotFoundEmptyResponseService {

  @GET
  public Response getDto() {
    return Response.status(NOT_FOUND).build();
  }

}
