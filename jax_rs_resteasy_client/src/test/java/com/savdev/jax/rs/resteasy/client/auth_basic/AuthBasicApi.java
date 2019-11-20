package com.savdev.jax.rs.resteasy.client.auth_basic;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.auth_basic.AuthBasicApi.AUTH_BASIC_API_PATH;


@Path(AUTH_BASIC_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthBasicApi {

  String AUTH_BASIC_API_PATH = "/rest/test/path";

  @GET
  RestDto getDto();
}
