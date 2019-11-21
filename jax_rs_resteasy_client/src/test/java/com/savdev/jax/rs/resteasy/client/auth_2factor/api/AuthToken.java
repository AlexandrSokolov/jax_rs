package com.savdev.jax.rs.resteasy.client.auth_2factor.api;

public class AuthToken {
  String jwtToken;

  public static AuthToken instance(String jwtToken){
    AuthToken token = new AuthToken();
    token.jwtToken = jwtToken;
    return token;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}
