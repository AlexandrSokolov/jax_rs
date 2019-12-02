package com.savdev.jax.rs.resteasy.client;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ClientRequestFilterFactory {

  public static final String BEARER_PREFIX = "Bearer ";

  private static final int CACHE_DURATION_MINUTES = 5;
  private static final int CACHE_SIZE = 1000;

  //https://stackoverflow.com/questions/51926704/why-is-guavas-eventbus-marked-unstable-in-intellij-2018-2
  private static Cache<String, String> login2token = CacheBuilder
    .newBuilder()
    .expireAfterWrite(CACHE_DURATION_MINUTES, TimeUnit.MINUTES)
    .build(); //no CacheLoader

  private static Cache<String, String> login2pwd = CacheBuilder
    .newBuilder()
    .maximumSize(CACHE_SIZE)
    .expireAfterWrite(CACHE_DURATION_MINUTES, TimeUnit.MINUTES)
    .build(); //no CacheLoader


  public static ClientRequestFilter basicAuthentication(String user, String password){
    return new BasicAuthentication(user, password);
  }

  /**
   * JWT Token is cached until
   *  - either it is expired OR
   *  - until password is changed.
   *
   * @param domain
   * @param login
   * @param password
   * @param authInterface
   * @param jwtAuthHeaderExtractor
   * @param <T>
   * @return
   */
  public static <T> ClientRequestFilter jwtThroughBasicAuthentication(
    final String domain,
    final String login,
    final String password,
    final Class<T> authInterface,
    final Function<T, String> jwtAuthHeaderExtractor){
    try {
      String savedPassword = login2pwd.get(login, () -> password);
      if (!savedPassword.equals(password)){
        login2pwd.invalidate(login);
        login2pwd.put(login, password);
        login2token.invalidate(login);
      }

      return jwt2factorCachedAuthentication(
        login,
        JaxRsProxyConfig
          .instance(domain)
          .clientRequestFilterAuthSupplier(() -> ClientRequestFilterFactory.basicAuthentication(login, password)),
        authInterface,
        jwtAuthHeaderExtractor);
    } catch (ExecutionException e) {
      throw new IllegalStateException(e);
    }

  }

  /**
   * The general function for 2 factor authentication (2 steps authentication).
   *
   * The first request is sent via the fstAuthRequest JaxRsProxyConfig, it returns proxy of <T> type.
   * Then, jwtAuthHeaderExtractor invokes a method of proxy, gets DTO and extracts JWT token value
   *
   * @param fstAuthRequest
   * @param authInterface
   * @param jwtAuthHeaderExtractor
   * @param <T>
   * @return
   */
  public static <T> ClientRequestFilter jwt2factorAuthentication(
    final JaxRsProxyConfig fstAuthRequest,
    final Class<T> authInterface,
    final Function<T, String> jwtAuthHeaderExtractor){
    return requestContext -> requestContext.getHeaders()
      .putSingle(HttpHeaders.AUTHORIZATION,
        BEARER_PREFIX + jwtAuthHeaderExtractor.apply(
          fstAuthRequest.proxy(authInterface)));
  }

  /**
   * The general function for 2 factor authentication (2 steps authentication).
   *
   *  It caches JWT token, it is updated, when it is expired.
   *
   * The first request is sent via the fstAuthRequest JaxRsProxyConfig, it returns proxy of <T> type.
   * Then, jwtAuthHeaderExtractor invokes a method of proxy, gets DTO and extracts JWT token value
   *
   * @param login
   * @param fstAuthRequest
   * @param authInterface
   * @param jwtAuthHeaderExtractor
   * @param <T>
   * @return
   */
  public static <T> ClientRequestFilter jwt2factorCachedAuthentication(
    final String login,
    final JaxRsProxyConfig fstAuthRequest,
    final Class<T> authInterface,
    final Function<T, String> jwtAuthHeaderExtractor){
    return requestContext -> {
      try {
        requestContext.getHeaders()
          .putSingle(HttpHeaders.AUTHORIZATION,
            login2token.get(login, () ->
              BEARER_PREFIX + jwtAuthHeaderExtractor.apply(
                fstAuthRequest.proxy(authInterface))));
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    };
  }

  private static class BasicAuthentication implements ClientRequestFilter {
    private final String authHeader;

    public BasicAuthentication(String username, String password) {
      try {
        this.authHeader = "Basic " + Base64.getEncoder().encodeToString(
          String.format("%s:%s", username, password)
            .getBytes(StandardCharsets.UTF_8.name()));
      } catch (UnsupportedEncodingException e) {
        throw new IllegalStateException(e);
      };
    }

    public void filter(ClientRequestContext requestContext) {
      requestContext.getHeaders().putSingle("Authorization", this.authHeader);
    }
  }
}
