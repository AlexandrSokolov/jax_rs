package com.savdev.jax.rs.resteasy.api;

import com.savdev.jax.rs.resteasy.dto.DtoGettersSettersWithoutJackson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import static com.savdev.jax.rs.resteasy.api.JaxRsGet.GET_PATH;

@Path(GET_PATH)
public interface JaxRsGet {

  String GET_PATH = "/get";

  /**
   *
   * URI Pattern for id = 999: “/get/999”,
   *
   * @param id
   * @return
   */
  @GET
  @Path("{id : \\d+}") //support digit only
  DtoGettersSettersWithoutJackson findById(long id);
}
