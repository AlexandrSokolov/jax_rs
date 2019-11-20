package com.savdev.jax.rs.resteasy.client.error.service;

import com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFoundWithTextBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path(ApiGetNotFoundWithTextBody.API_GET_NOT_FOUND_WITH_TEXT_BODY_PATH)
public class NotFoundWithTextResponseService {

  public static final String NOT_FOUND_RESPONSE_BODY = "NOT FOUND OBJECT. TST B";

  @GET
  public Response getDto() {
    return Response.status(NOT_FOUND).entity(NOT_FOUND_RESPONSE_BODY).build();
  }
}
