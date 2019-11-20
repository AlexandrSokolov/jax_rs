package com.savdev.jax.rs.resteasy.client;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;

import javax.ws.rs.client.ClientRequestFilter;

public class ClientRequestFilterFactory {

  public static ClientRequestFilter basicAuthentication(String user, String password){
    return new BasicAuthentication(user, password);
  }
}
