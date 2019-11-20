package com.savdev.jax.rs.resteasy.client.provide_config.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.provide_config.api.ProvideConfigApiServer.PROVIDE_CONFIG_API_PATH;


@Path(PROVIDE_CONFIG_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface ProvideConfigApiServer {

  String PROVIDE_CONFIG_API_PATH = "/rest/test/path/provide/config";

  @GET
  Response restDto();

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  Response updateDto(String dtoAsString);
}
