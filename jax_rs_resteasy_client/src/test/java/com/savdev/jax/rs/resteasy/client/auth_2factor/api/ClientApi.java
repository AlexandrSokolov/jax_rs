package com.savdev.jax.rs.resteasy.client.auth_2factor.api;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.auth_2factor.api.ClientApi.CLIENT_API_PATH;

@Path(CLIENT_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientApi {

  String CLIENT_API_PATH = "/rest/test/path/test/jwt/client";

  @GET
  RestDto getDto();
}
