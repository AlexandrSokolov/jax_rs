package com.savdev.jax.rs.resteasy.client;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder;
import org.junit.AfterClass;

import javax.ws.rs.core.UriBuilder;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

public class JaxRsProxyConfigBaseTest {

  private static final String DOMAIN = "http://localhost";
  private static final int HTTP_PORT = 8089;

  protected static final java.net.URI URI = UriBuilder
    .fromUri(DOMAIN)
    .port(HTTP_PORT)
    .build();

  private static HttpServer server;
  private static HttpContextBuilder contextBuilder;

  public static void setUpServer(final Collection<Class<?>> types) throws Exception {
    setUpServer(types, Collections.emptyList());
  }

  public static void setUpServer(
    final Collection<Class<?>> types,
    final Collection<Class<?>> providerTypes) throws Exception {
    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
    System.setProperty("org.apache.commons.logging.simplelog.log.com.savdev.jax.rs.resteasy.client.filter", "DEBUG");

    // create a new server listening at port 8089
    server = HttpServer.create(new InetSocketAddress(URI.getPort()), 10);
    contextBuilder = new HttpContextBuilder();
    // deploy server
    contextBuilder.getDeployment().getActualResourceClasses().addAll(types);
    contextBuilder.getDeployment().getActualProviderClasses().addAll(providerTypes);

    final HttpContext context = contextBuilder.bind(server);
    // context.getAttributes().put("some.config.info", "42");
    // start the server
    server.start();
  }

  @AfterClass
  public static void tearDown() {
    // stop server
    contextBuilder.cleanup();
    server.stop(0);
  }
}
