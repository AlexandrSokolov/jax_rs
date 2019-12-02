package com.savdev.jax.rs.resteasy.client.custom_debug_mode;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.custom_debug_mode.CustomDebugApi.CUSTOM_DEBUG_API_PATH;


@Path(CUSTOM_DEBUG_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface CustomDebugApi {

  String CUSTOM_DEBUG_API_PATH = "/rest/custom/debug/test/path";

  @GET
  RestDto getDto();
}
