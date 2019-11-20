package com.savdev.jax.rs.resteasy.client.custom_json;

import com.google.common.collect.Lists;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.custom_json.api.CustomJsonApiClient;
import com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiClient;
import com.savdev.jax.rs.resteasy.client.custom_json.service.CustomJsonService;
import com.savdev.jax.rs.resteasy.client.custom_json.service.WrongCustomJsonService;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;

import static com.savdev.jax.rs.resteasy.client.custom_json.api.WrongCustomJsonApiServer.WRONG_CUSTOM_JSON_MEDIA_TYPE;

public class CustomJsonTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(Lists.newArrayList(
      CustomJsonService.class,
      WrongCustomJsonService.class));
  }

  @Test
  public void testGet(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto dto = proxyConfig.proxy(CustomJsonApiClient.class).getDto();
    Assert.assertEquals(CustomJsonService.INSTANCE_TO_UPDATE.getName(), dto.getName());
  }

  @Test
  public void testPut(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto dto = proxyConfig.proxy(CustomJsonApiClient.class).updateDto(CustomJsonService.INSTANCE_TO_UPDATE);
    Assert.assertNotEquals(CustomJsonService.INSTANCE_TO_UPDATE.getName(), dto.getName());
    Assert.assertEquals(dto.getName(), CustomJsonService.UPDATE_PREFIX + CustomJsonService.INSTANCE_TO_UPDATE.getName());
  }

  /**
   * Request is sent successfully, server handles it successfully, but client cannot deserialize it,
   * cause it does not know anything about the custom type
   */
  @Test
  public void testNoMessageBodyWriterException(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
      proxyConfig.proxy(WrongCustomJsonApiClient.class).getDto();
      Assert.fail("Default JaxRsProxyConfig must be able to handle only JSON content type");
    } catch (Exception e) {
      Assert.assertEquals(ResponseProcessingException.class, e.getClass());
      Assert.assertEquals(
        String.format("%s: RESTEASY003145: Unable to find a MessageBodyReader of content-type %s and type class %s",
          ProcessingException.class.getName(),
          WRONG_CUSTOM_JSON_MEDIA_TYPE,
          RestDto.class.getName()),
        e.getMessage());
    }
  }

  /**
   * Request is not even sent, cause the body cannot be created.
   */
  @Test
  public void testCouldNotFindWriterForCustomContentTypeException(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
      proxyConfig.proxy(WrongCustomJsonApiClient.class).updateDto(CustomJsonService.INSTANCE_TO_UPDATE);
    } catch (Exception e){
      Assert.assertEquals(ProcessingException.class, e.getClass());
      Assert.assertEquals(
        String.format("RESTEASY003215: could not find writer for content-type %s type: %s",
          WRONG_CUSTOM_JSON_MEDIA_TYPE,
          RestDto.class.getName()),
        e.getCause().getMessage());
    }
  }

}
