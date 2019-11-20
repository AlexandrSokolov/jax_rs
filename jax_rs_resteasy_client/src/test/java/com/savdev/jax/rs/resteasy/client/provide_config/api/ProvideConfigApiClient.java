package com.savdev.jax.rs.resteasy.client.provide_config.api;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.provide_config.api.ProvideConfigApiServer.PROVIDE_CONFIG_API_PATH;


@Path(PROVIDE_CONFIG_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface ProvideConfigApiClient {


  @GET
  RestDto getDto();

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  RestDto updateDto(RestDto dto);
}
