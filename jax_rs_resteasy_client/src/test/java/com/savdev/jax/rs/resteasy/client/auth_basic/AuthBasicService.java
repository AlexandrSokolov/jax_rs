package com.savdev.jax.rs.resteasy.client.auth_basic;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class AuthBasicService implements AuthBasicApi {

  static final String LOGIN = "testLogin";
  static final String PASSWORD = "testPassword";

  static final String TEST_NAME = "Auth Basic testName";
  static final BigDecimal TEST_BD = new BigDecimal("125.22");
  static final Date TEST_DATE = Calendar.getInstance().getTime();

  static final String BASIC_PREFIX = "Basic ";

  static final RestDto AB_INSTANCE = RestDto.instance(TEST_NAME, TEST_DATE, TEST_BD);

  @Context
  HttpHeaders headers;

  @Override
  public RestDto getDto() {
    try {
      if (headers.getRequestHeader(HttpHeaders.AUTHORIZATION) == null
        || headers.getRequestHeader(HttpHeaders.AUTHORIZATION).isEmpty()) {
        throw new IllegalStateException("Not authenticated");
      } else {
        String authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
        authHeader = authHeader.startsWith(BASIC_PREFIX)
          ? authHeader.substring(BASIC_PREFIX.length())
          : authHeader;

        if (!authHeader.equals(
          Base64.getEncoder().encodeToString(
            String.format("%s:%s", LOGIN, PASSWORD)
              .getBytes(StandardCharsets.UTF_8.name())))) {
          throw new IllegalStateException("Not authenticated");
        } else {
          return AB_INSTANCE;
        }
      }
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }
}
