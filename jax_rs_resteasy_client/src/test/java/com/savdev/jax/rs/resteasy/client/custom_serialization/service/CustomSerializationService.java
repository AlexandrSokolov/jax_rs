package com.savdev.jax.rs.resteasy.client.custom_serialization.service;

import com.savdev.jax.rs.resteasy.client.custom_serialization.api.CustomSerializationApiServer;

import javax.ws.rs.core.Response;

public class CustomSerializationService implements CustomSerializationApiServer {

  public static final String DATE_STRING = "2018-12-04T02:30Z";
  public static final String NAME = "test name";

  public static final String REST_DTO_STRING = String.format("{\n" +
    "    \"name\" : \"%s\",\n" +
    "    \"date\" : \"%s\",\n" +
    "    \"bigDecimal\" : 322.22\n" +
    "  }", NAME, DATE_STRING);

  public static final String UPDATED_NAME = "updated name";
  public static final String UPDATED_DATE_STRING = "2019-10-24T02:30Z";

  public static final String UPDATED_REST_DTO_STRING = String.format("{\n" +
    "    \"name\" : \"%s\",\n" +
    "    \"date\" : \"%s\",\n" +
    "    \"bigDecimal\" : 322.22\n" +
    "  }", UPDATED_NAME, UPDATED_DATE_STRING);

  @Override
  public Response restDto() {
    return Response.ok().entity(REST_DTO_STRING).build();
  }

  @Override
  public Response updateDto(String ignored) {
    return Response.ok().entity(UPDATED_REST_DTO_STRING).build();
  }


}
