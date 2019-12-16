package com.savdev.jax.rs.resteasy.server.config;

import com.google.common.collect.Sets;
import com.savdev.jax.rs.resteasy.server.service.JaxRsGetService;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Full url to rest services:
 * ${CONTEXT_ROOT}/${APPLICATION_PATH}/${REST_SERVICE_PATH}
 *
 * Where ${CONTEXT_ROOT} - depends on the application server:
 *  for Wildfly it is src/main/webapp/WEB-INF/jboss-web.xml
 *
 * ${APPLICATION_PATH} - path configured for Jax Rs configuration with @ApplicationPath annotation
 * ${REST_SERVICE_PATH} - path configured for a Jax Rs service/method with @Path annotation
 */
@ApplicationPath(JaxRsConfiguration.APPLICATION_PATH)
public class JaxRsConfiguration extends Application {

  //see src/main/webapp/WEB-INF/jboss-web.xml
  public static final String CONTEXT_ROOT = "/appContext";
  public static final String APPLICATION_PATH = "/appPath";

  @Override
  public Set<Class<?>> getClasses() {
    return Sets.newHashSet(
      JaxRsGetService.class,
      OpenApiResource.class);
  }
}
