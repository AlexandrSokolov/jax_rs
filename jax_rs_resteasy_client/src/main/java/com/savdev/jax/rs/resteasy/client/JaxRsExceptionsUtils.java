package com.savdev.jax.rs.resteasy.client;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;

import javax.ws.rs.WebApplicationException;

public class JaxRsExceptionsUtils {

  public static String extractErrorFromResponse(final Exception e){
    if (e instanceof WebApplicationException){
      WebApplicationException we = (WebApplicationException) e;
      return RequestResponseInfo.errorRequestResponseInfo(we.getResponse())
        .orElseThrow(() -> new IllegalStateException("Not nullable instance of RequestResponseInfo is expected."));
    } else {
      return e.getMessage();
    }
  }
}
