package com.savdev.jax.rs.resteasy.server.config;

import com.google.common.collect.Sets;
import com.savdev.jax.rs.resteasy.server.service.JaxRsGetService;
import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Optional;
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
  public static final String SWAGGER_CONFIGURATION = "swagger.configuration.yaml";

  public JaxRsConfiguration() {
    swaggerConfigPath().ifPresent(path -> {
    try {
      new JaxrsOpenApiContextBuilder()
        .configLocation(path)
        .buildContext(true);
    } catch (OpenApiConfigurationException e) {
      throw new RuntimeException(e.getMessage(), e);
    }});
  }

  @Override
  public Set<Class<?>> getClasses() {
    return Sets.newHashSet(
      JaxRsGetService.class,
      OpenApiResource.class);
  }

  private Optional<String> swaggerConfigPath(){
    //you can customize paths, for instance by passing a certain path via system variables,
    //then applying it for custom configuration
    //config files from default location have higher priority.
    //If you have them, your custom file will be ignored
    return Optional.of(SWAGGER_CONFIGURATION);
  }
}
