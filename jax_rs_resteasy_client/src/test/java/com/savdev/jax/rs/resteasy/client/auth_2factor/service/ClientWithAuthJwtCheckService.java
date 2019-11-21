package com.savdev.jax.rs.resteasy.client.auth_2factor.service;

import com.savdev.jax.rs.resteasy.client.auth_2factor.api.ClientApi;
import com.savdev.jax.rs.resteasy.client.auth_basic.AuthBasicApi;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import static com.savdev.jax.rs.resteasy.client.ClientRequestFilterFactory.BEARER_PREFIX;

public class ClientWithAuthJwtCheckService implements ClientApi {

  static final String TEST_NAME = "Auth Jwt testName";
  static final BigDecimal TEST_BD = new BigDecimal("358.298");
  static final Date TEST_DATE = Calendar.getInstance().getTime();

  public static final RestDto JWT_INSTANCE = RestDto.instance(TEST_NAME, TEST_DATE, TEST_BD);

  @Context
  HttpHeaders headers;

  @Override
  public RestDto getDto() {
    if (headers.getRequestHeader(HttpHeaders.AUTHORIZATION) == null
      || headers.getRequestHeader(HttpHeaders.AUTHORIZATION).isEmpty()) {
      throw new IllegalStateException("Not authenticated");
    } else {
      String authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
      authHeader = authHeader.startsWith(BEARER_PREFIX)
        ? authHeader.substring(BEARER_PREFIX.length())
        : authHeader;

      if (!authHeader.equals(AuthJwtService.JWT_AUTH_TOKEN.getJwtToken())) {
        throw new IllegalStateException("Not authenticated");
      } else {
        return JWT_INSTANCE;
      }
    }
  }
}
