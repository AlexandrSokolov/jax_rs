package com.savdev.jax.rs.resteasy.client.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFound;
import com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFoundWithJsonBody;
import com.savdev.jax.rs.resteasy.client.error.api.ApiGetNotFoundWithTextBody;
import com.savdev.jax.rs.resteasy.client.error.service.NotFoundEmptyResponseService;
import com.savdev.jax.rs.resteasy.client.error.service.NotFoundWithJsonResponseService;
import com.savdev.jax.rs.resteasy.client.error.service.NotFoundWithTextResponseService;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.Map;

import static com.savdev.jax.rs.resteasy.client.error.service.NotFoundWithJsonResponseService.ERROR_DTO;
import static com.savdev.jax.rs.resteasy.client.error.service.NotFoundWithTextResponseService.NOT_FOUND_RESPONSE_BODY;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class JaxRsProxyConfigErrorTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(Lists.newArrayList(
      NotFoundEmptyResponseService.class,
      NotFoundWithTextResponseService.class,
      NotFoundWithJsonResponseService.class));
  }

  @Test
  public void testNotFoundEmptyResponse() throws IOException {
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    try {
      proxyConfig.proxy(ApiGetNotFound.class).getDto();
      Assert.fail("Exception must be thrown");
    } catch (Exception e){
      Assert.assertEquals(new NotFoundException().getMessage(), e.getMessage());
      Assert.assertTrue(e instanceof WebApplicationException);
      WebApplicationException we = (WebApplicationException) e;
      String requestResponseInfo = RequestResponseInfo.errorRequestResponseInfo(we.getResponse())
        .orElseThrow(() -> new IllegalStateException("Not nullable instance of RequestResponseInfo is expected."));
      @SuppressWarnings("unchecked")
      Map<String, Object> asMap = new ObjectMapper().readValue(requestResponseInfo, Map.class);
      Assert.assertEquals(NOT_FOUND.getStatusCode(), asMap.get("responseStatus"));
      Assert.assertTrue(asMap.containsKey("responseBody"));
      Assert.assertNull(asMap.get("responseBody"));
    }
  }

  @Test
  public void testNotFoundWithTextResponseBody() throws IOException {
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    try {
      proxyConfig.proxy(ApiGetNotFoundWithTextBody.class).getDto();
      Assert.fail("Exception must be thrown");
    } catch (Exception e){
      Assert.assertEquals(new NotFoundException().getMessage(), e.getMessage());
      Assert.assertTrue(e instanceof WebApplicationException);
      WebApplicationException we = (WebApplicationException) e;
      String requestResponseInfo = RequestResponseInfo.errorRequestResponseInfo(we.getResponse())
        .orElseThrow(() -> new IllegalStateException("Not nullable instance of RequestResponseInfo is expected."));
      @SuppressWarnings("unchecked")
      Map<String, Object> asMap = new ObjectMapper().readValue(requestResponseInfo, Map.class);
      Assert.assertEquals(NOT_FOUND.getStatusCode(), asMap.get("responseStatus"));
      Assert.assertEquals(NOT_FOUND_RESPONSE_BODY, asMap.get("responseBody"));
    }
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testNotFoundWithJsonResponseBody() throws IOException {
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    try {
      proxyConfig.proxy(ApiGetNotFoundWithJsonBody.class).getDto();
      Assert.fail("Exception must be thrown");
    } catch (Exception e){
      Assert.assertEquals(new NotFoundException().getMessage(), e.getMessage());
      Assert.assertTrue(e instanceof WebApplicationException);
      WebApplicationException we = (WebApplicationException) e;
      String requestResponseInfo = RequestResponseInfo.errorRequestResponseInfo(we.getResponse())
        .orElseThrow(() -> new IllegalStateException("Not nullable instance of RequestResponseInfo is expected."));

      Map<String, Object> asMap = new ObjectMapper().readValue(requestResponseInfo, Map.class);

      Assert.assertTrue(asMap.containsKey("responseBody"));
      Assert.assertTrue(((Map<String, String>) asMap.get("responseBody")).containsKey("errorDescription"));
      Assert.assertEquals(
        ERROR_DTO.getErrorDescription(),
        ((Map<String, String>)asMap.get("responseBody")).get("errorDescription"));
    }
  }
}
