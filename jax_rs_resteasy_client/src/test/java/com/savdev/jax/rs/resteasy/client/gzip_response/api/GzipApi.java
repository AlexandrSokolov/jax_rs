package com.savdev.jax.rs.resteasy.client.gzip_response.api;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.gzip_response.api.GzipApi.GZIP_API_PATH;


@Path(GZIP_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface GzipApi {

  String GZIP_API_PATH = "/rest/gzip/test/path";

  @GET
  RestDto getDto();
}
