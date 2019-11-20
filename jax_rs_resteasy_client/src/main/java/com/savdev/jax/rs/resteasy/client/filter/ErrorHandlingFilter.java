package com.savdev.jax.rs.resteasy.client.filter;

import com.google.common.collect.Sets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;

public class ErrorHandlingFilter implements ClientResponseFilter {

  private static final Log logger = LogFactory.getLog(ErrorHandlingFilter.class);

  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
    if (Sets.newHashSet(
      Response.Status.Family.CLIENT_ERROR,
      Response.Status.Family.SERVER_ERROR)
      .contains(Response.Status.Family.familyOf(responseContext.getStatus()))) {
      RequestResponseInfo requestResponseInfo = RequestResponseInfo.instance(requestContext, responseContext);
      logger.error(requestResponseInfo);

      responseContext
        .getHeaders()
        .putSingle(
          RequestResponseInfo.ERROR_HEADER,
          requestResponseInfo.toString());
    }
  }
}
