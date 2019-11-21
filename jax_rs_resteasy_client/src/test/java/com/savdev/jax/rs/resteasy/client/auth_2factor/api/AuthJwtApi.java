package com.savdev.jax.rs.resteasy.client.auth_2factor.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.auth_2factor.api.AuthJwtApi.AUTH_JWT_PATH;

@Path(AUTH_JWT_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthJwtApi {

  String AUTH_JWT_PATH = "/path/test/jwt/auth/service";

  @GET
  AuthToken authenticate();
}
