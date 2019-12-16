package com.savdev.jax.rs.resteasy.api;

import com.savdev.jax.rs.resteasy.dto.without.jackson.DtoGettersSettersWithoutJackson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.api.JaxRsGet.GET_PATH;

@Path(GET_PATH)
@Produces(MediaType.APPLICATION_JSON)
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
  DtoGettersSettersWithoutJackson findById(@PathParam("id") long id);
}
