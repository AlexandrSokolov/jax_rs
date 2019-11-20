package com.savdev.jax.rs.resteasy.client;


import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ClientRequestFilterFactory {

  public static ClientRequestFilter basicAuthentication(String user, String password){
    return new BasicAuthentication(user, password);
  }

  private static class BasicAuthentication implements ClientRequestFilter {
    private final String authHeader;

    public BasicAuthentication(String username, String password) {
      try {
        this.authHeader = Base64.getEncoder().encodeToString(
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
