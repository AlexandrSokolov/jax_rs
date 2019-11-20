package com.savdev.jax.rs.resteasy.client.error.api;

import com.savdev.jax.rs.resteasy.client.success.api.Api;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFoundWithTextBody.API_GET_NOT_FOUND_WITH_TEXT_BODY_PATH;

@Path(API_GET_NOT_FOUND_WITH_TEXT_BODY_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface ApiGetNotFoundWithTextBody extends Api {


  String API_GET_NOT_FOUND_WITH_TEXT_BODY_PATH = "/rest/get/path/not/found/with/body";
}
