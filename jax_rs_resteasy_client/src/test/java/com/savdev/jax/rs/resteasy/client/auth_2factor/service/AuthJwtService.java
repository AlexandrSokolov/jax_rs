package com.savdev.jax.rs.resteasy.client.auth_2factor.service;

import com.savdev.jax.rs.resteasy.client.auth_2factor.api.AuthJwtApi;
import com.savdev.jax.rs.resteasy.client.auth_2factor.api.AuthToken;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthJwtService implements AuthJwtApi {

  public static final String JWT_LOGIN = "testJwtLogin";
  public static final String JWT_PASSWORD = "testJwtPassword";

  static final String AUTH_BASIC_PREFIX = "Basic ";

  public static final AuthToken JWT_AUTH_TOKEN = AuthToken.instance("someGenaratedToken");

  @Context
  HttpHeaders headers;

  @Override
  public AuthToken authenticate() {
    try {
      if (headers.getRequestHeader(HttpHeaders.AUTHORIZATION) == null
        || headers.getRequestHeader(HttpHeaders.AUTHORIZATION).isEmpty()) {
        throw new IllegalStateException("Not authenticated via jwt");
      } else {
        String authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
        authHeader = authHeader.startsWith(AUTH_BASIC_PREFIX)
          ? authHeader.substring(AUTH_BASIC_PREFIX.length())
          : authHeader;

        if (!authHeader.equals(
          Base64.getEncoder().encodeToString(
            String.format("%s:%s", JWT_LOGIN, JWT_PASSWORD)
              .getBytes(StandardCharsets.UTF_8.name())))) {
          throw new IllegalStateException("Not authenticated via jwt");
        } else {
          return JWT_AUTH_TOKEN;
        }
      }
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }
}
