package com.savdev.jax.rs.resteasy.client.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class RequestResponseLogFilter implements ClientResponseFilter {

  private static final Log logger = LogFactory.getLog(RequestResponseLogFilter.class);

  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
    if (logger.isDebugEnabled()) {
      logger.debug(RequestResponseInfo.instance(requestContext, responseContext));
    }
  }
}
