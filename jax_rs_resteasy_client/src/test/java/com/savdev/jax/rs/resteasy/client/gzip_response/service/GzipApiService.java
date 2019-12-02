package com.savdev.jax.rs.resteasy.client.gzip_response.service;

import com.savdev.jax.rs.resteasy.client.dto.RestDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static com.savdev.jax.rs.resteasy.client.gzip_response.api.GzipApi.GZIP_API_PATH;

@Path(GZIP_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class GzipApiService {

  public static final String TEST_NAME = "testName gzip";
  public static final BigDecimal TEST_BD = new BigDecimal("345.22");
  public static final Date TEST_DATE = Calendar.getInstance().getTime();

  public static final RestDto INSTANCE = RestDto.instance(TEST_NAME, TEST_DATE, TEST_BD);

  /**
   * The content itself will be gzipped by:
   *  org.jboss.resteasy.plugins.interceptors.GZIPEncodingInterceptor
   *  when "Content-Encoding" response header is set to "gzip"
   *
   * @return
   */
  @GET
  public Response getDto() {
    return Response.ok()
      .entity(INSTANCE)
      .header(HttpHeaders.CONTENT_ENCODING, "gzip")
      .build();
  }
}
