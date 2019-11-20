package com.savdev.jax.rs.resteasy.client.error.api;

import com.savdev.jax.rs.resteasy.client.success.api.Api;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFound.API_GET_NOT_FOUND_PATH;

@Path(API_GET_NOT_FOUND_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface ApiGetNotFound extends Api {

  String API_GET_NOT_FOUND_PATH = "/rest/get/path/not/found";
}
