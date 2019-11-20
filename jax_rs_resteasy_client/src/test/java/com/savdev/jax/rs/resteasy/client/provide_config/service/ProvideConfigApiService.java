package com.savdev.jax.rs.resteasy.client.provide_config.service;

import com.savdev.jax.rs.resteasy.client.provide_config.api.ProvideConfigApiServer;

import javax.ws.rs.core.Response;

public class ProvideConfigApiService implements ProvideConfigApiServer {

  public static final String PC_DATE_STRING = "2015-07-04T02:30Z";
  public static final String PC_NAME = "test name pc";

  public static final String REST_DTO_STRING = String.format("{\n" +
    "    \"name\" : \"%s\",\n" +
    "    \"date\" : \"%s\",\n" +
    "    \"bigDecimal\" : 322.22\n" +
    "  }", PC_NAME, PC_DATE_STRING);

  public static final String PC_UPDATED_NAME = "PC updated name";
  public static final String PC_UPDATED_DATE_STRING = "2017-11-01T02:30Z";

  public static final String PC_UPDATED_REST_DTO_STRING = String.format("{\n" +
    "    \"name\" : \"%s\",\n" +
    "    \"date\" : \"%s\",\n" +
    "    \"bigDecimal\" : 322.22\n" +
    "  }", PC_UPDATED_NAME, PC_UPDATED_DATE_STRING);

  @Override
  public Response restDto() {
    return Response.ok().entity(REST_DTO_STRING).build();
  }

  @Override
  public Response updateDto(String ignored) {
    return Response.ok().entity(PC_UPDATED_REST_DTO_STRING).build();
  }

}
