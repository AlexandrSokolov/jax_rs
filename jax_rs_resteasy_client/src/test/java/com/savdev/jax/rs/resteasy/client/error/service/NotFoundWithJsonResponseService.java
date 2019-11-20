package com.savdev.jax.rs.resteasy.client.error.service;

import com.savdev.jax.rs.resteasy.client.dto.ErrorDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFoundWithJsonBody.API_GET_NOT_FOUND_WITH_JSON_BODY_PATH;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path(API_GET_NOT_FOUND_WITH_JSON_BODY_PATH)
public class NotFoundWithJsonResponseService {

  public static final ErrorDto ERROR_DTO = ErrorDto.instance("NOT FOUND INSIDE JSON");

  @GET
  public Response getDto() {
    return Response
      .status(NOT_FOUND)
      .entity(ERROR_DTO)
      .build();
  }
}
