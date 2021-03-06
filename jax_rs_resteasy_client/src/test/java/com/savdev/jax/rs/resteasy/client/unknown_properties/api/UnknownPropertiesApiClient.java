package com.savdev.jax.rs.resteasy.client.unknown_properties.api;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.dto.RestDtoExtraProps;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.jax.rs.resteasy.client.unknown_properties.api.UnknownPropertiesApiServer.UNKNONWN_PROPS_API_PATH;
import static com.savdev.jax.rs.resteasy.client.unknown_properties.api.UnknownPropertiesApiServer.UNKNONWN_PROPS_SMALLER_FIELDS_API_PATH;

@Path(UNKNONWN_PROPS_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface UnknownPropertiesApiClient {

  /**
   * Server returns a dto with property, not known to the client.
   * @return
   */
  @GET
  RestDto getDtoWithAdditionalFieldFromServer();

  /**
   * Server does not send a certain field in the response
   * @return
   */
  @GET
  @Path(UNKNONWN_PROPS_SMALLER_FIELDS_API_PATH)
  RestDtoExtraProps getDtoWithoutCertainField();
}
