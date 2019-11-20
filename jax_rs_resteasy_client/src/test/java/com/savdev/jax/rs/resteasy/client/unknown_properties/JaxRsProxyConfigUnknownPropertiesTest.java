package com.savdev.jax.rs.resteasy.client.unknown_properties;

import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.unknown_properties.api.UnknownPropertiesApiClient;
import com.savdev.jax.rs.resteasy.client.unknown_properties.service.UnknownPropertiesService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

public class JaxRsProxyConfigUnknownPropertiesTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(UnknownPropertiesService.class));
  }

  /**
   * Both UnknownPropertiesApiClient and UnknownPropertiesApiServer use the same path,
   * but different DTO object. Server returns RestDtoExtraProps DTO with additional lValue field.
   * Client expects RestDto, which is a parent for RestDtoExtraProps.
   *
   * By default, it is not allowed. We configure our JaxRsProxyConfig with:
   *   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   *
   * In test we make sure, it is applied by default
   */
  @Test
  public void testUnknownPropertiesFromServer(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto restDto = proxyConfig.proxy(UnknownPropertiesApiClient.class).getDtoWithAdditionalFieldFromServer();
    Assert.assertNotNull(restDto);
    Assert.assertEquals(UnknownPropertiesService.INSTANCE_WITH_UNKNOWN_FIELDS.getName(), restDto.getName());
    Assert.assertEquals(UnknownPropertiesService.INSTANCE_WITH_UNKNOWN_FIELDS.getBigDecimal(), restDto.getBigDecimal());
    Assert.assertEquals(UnknownPropertiesService.INSTANCE_WITH_UNKNOWN_FIELDS.getDate(), restDto.getDate());
  }

  /**
   * Now server sends a dto, which does not contain a field, that client expects.
   * The lValue field, not known to the server is just set to default!!
   *
   */
  @Test
  public void testUnknownPropertiesForServer(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto restDto = proxyConfig.proxy(UnknownPropertiesApiClient.class).getDtoWithoutCertainField();
    Assert.assertNotNull(restDto);
    Assert.assertEquals(UnknownPropertiesService.INSTANCE_WITH_FEWER_FIELDS.getName(), restDto.getName());
    Assert.assertEquals(UnknownPropertiesService.INSTANCE_WITH_FEWER_FIELDS.getBigDecimal(), restDto.getBigDecimal());
    Assert.assertEquals(UnknownPropertiesService.INSTANCE_WITH_FEWER_FIELDS.getDate(), restDto.getDate());
  }
}
