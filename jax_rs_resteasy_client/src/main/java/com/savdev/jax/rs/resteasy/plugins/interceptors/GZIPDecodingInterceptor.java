package com.savdev.jax.rs.resteasy.plugins.interceptors;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * The copied version of the org.jboss.resteasy.plugins.interceptors.encoding.GZIPDecodingInterceptor
 * Existing in resteasy version 3.0.x and moved to org.jboss.resteasy.plugins.interceptors.GZIPDecodingInterceptor
 * in the resteasy version 3.1.x
 * To avoid ClassNotFound exceptions
 */
@Provider
@Priority(Priorities.ENTITY_CODER)
public class GZIPDecodingInterceptor implements ReaderInterceptor {
  public static class FinishableGZIPInputStream extends GZIPInputStream {
    public FinishableGZIPInputStream(final InputStream is) throws IOException {
      super(is);
    }

    public void finish() {
      inf.end(); // make sure on finish the inflater's end() is called to release the native code pointer
    }
  }

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
    Object encoding = context.getHeaders().getFirst(HttpHeaders.CONTENT_ENCODING);

    if (encoding != null && encoding.toString().equalsIgnoreCase("gzip")) {
      InputStream old = context.getInputStream();
      FinishableGZIPInputStream is = new FinishableGZIPInputStream(old);
      context.setInputStream(is);
      try {
        return context.proceed();
      } finally {
        // Don't finish() an InputStream - TODO this still will require a garbage collect to finish the stream
        // see RESTEASY-554 for more details
        if (!context.getType().equals(InputStream.class)) is.finish();
        context.setInputStream(old);
      }
    } else {
      return context.proceed();
    }
  }
}

