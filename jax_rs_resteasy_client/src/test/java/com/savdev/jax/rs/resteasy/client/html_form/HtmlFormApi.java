package com.savdev.jax.rs.resteasy.client.html_form;


import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.math.BigDecimal;

import static com.savdev.jax.rs.resteasy.client.html_form.HtmlFormApi.HTML_FORM_API_PATH;


@Path(HTML_FORM_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface HtmlFormApi {

  String HTML_FORM_API_PATH = "/rest/html/form/test/path";

  /**
   * We cannot accept a parameter as RestDto class, but as list of form fields
   * @return
   */
  @PUT
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  RestDto updateDto(
    @FormParam("name") String name,
    @FormParam("bigDecimal") BigDecimal bigDecimal);

}
