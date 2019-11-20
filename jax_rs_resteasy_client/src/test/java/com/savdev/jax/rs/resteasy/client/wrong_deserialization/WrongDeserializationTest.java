package com.savdev.jax.rs.resteasy.client.wrong_deserialization;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.wrong_deserialization.api.WrongDeserializationApiClient;
import com.savdev.jax.rs.resteasy.client.wrong_deserialization.service.WrongDeserializationService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import java.util.Collections;

public class WrongDeserializationTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(WrongDeserializationService.class));
  }

  /**
   * Make sure that the error is not hidden with the jax rs config proxy.
   *
   */
  @Test
  public void testWrongDeserialization(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
      proxyConfig.proxy(WrongDeserializationApiClient.class).getDto();
      Assert.fail("An exception is expected");
    } catch (Exception e){
      Assert.assertEquals(ResponseProcessingException.class, e.getClass());
      Assert.assertTrue(e.getMessage().contains("Cannot deserialize"));
      Assert.assertTrue(e.getMessage().contains(RestDto.class.getName()));
    }

  }
}
