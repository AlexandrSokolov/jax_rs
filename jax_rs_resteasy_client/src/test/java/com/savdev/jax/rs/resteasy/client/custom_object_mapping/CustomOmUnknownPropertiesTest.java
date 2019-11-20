package com.savdev.jax.rs.resteasy.client.custom_object_mapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.custom_object_mapping.api.CustomOmUnknownPropertiesApiClient;
import com.savdev.jax.rs.resteasy.client.custom_object_mapping.service.CustomOmUnknownPropertiesService;
import com.savdev.jax.rs.resteasy.client.dto.RestDtoExtraProps;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ResponseProcessingException;
import java.util.Collections;

public class CustomOmUnknownPropertiesTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(CustomOmUnknownPropertiesService.class));
  }

  /**
   * Both CustomOmUnknownPropertiesApiClient and CustomOmUnknownPropertiesApiServer use the same path,
   * but different DTO object. Server returns RestDtoExtraProps DTO with additional lValue field.
   * Client expects RestDto, which is a parent for RestDtoExtraProps.
   *
   * By default, it is not allowed. We configure our JaxRsProxyConfig by default with:
   *   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   *
   *
   * but our client does want to have a default behaviour from Jackson, but not default behaviour of JaxRsProxyConfig.
   * So he must explicitly set:
   *
   * mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
   *
   */
  @Test
  public void testUnknownPropertiesFromServer(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
        .objectMapperConsumer(om -> om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true));
      proxyConfig.proxy(CustomOmUnknownPropertiesApiClient.class).getDtoWithAdditionalFieldFromServer();
      Assert.fail("error is expected");
    } catch (Exception e){
      Assert.assertEquals(ResponseProcessingException.class, e.getClass());
      Assert.assertTrue(e.getMessage().contains("Unrecognized field \"lValue\""));
    }
  }

  /**
   * Now server sends a dto, which does not contain a field, that client expects.
   * The lValue field, not known to the server is just set to default.
   * No errors even with:
   *
   * objectMapper.configure(SerializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
   */
  @Test
  public void testUnknownPropertiesForServer(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .objectMapperConsumer(om -> om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true));
    RestDtoExtraProps restDto = proxyConfig.proxy(CustomOmUnknownPropertiesApiClient.class).getDtoWithoutCertainField();
    Assert.assertNotNull(restDto);
    Assert.assertEquals(CustomOmUnknownPropertiesService.CO_INSTANCE_WITH_FEWER_FIELDS.getName(), restDto.getName());
    Assert.assertEquals(CustomOmUnknownPropertiesService.CO_INSTANCE_WITH_FEWER_FIELDS.getBigDecimal(), restDto.getBigDecimal());
    Assert.assertEquals(CustomOmUnknownPropertiesService.CO_INSTANCE_WITH_FEWER_FIELDS.getDate(), restDto.getDate());
  }
}
