package com.savdev.jax.rs.resteasy.client.success;

import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.success.api.Api;
import com.savdev.jax.rs.resteasy.client.success.service.ApiService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

public class JaxRsProxyConfigSuccessTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(ApiService.class));
  }

  @Test
  public void testSimpleGet(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto dto = proxyConfig.proxy(Api.class).getDto();
    Assert.assertEquals(ApiService.INSTANCE, dto);
  }

  @Test
  public void testPut(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto dto = proxyConfig.proxy(Api.class).updateDto(ApiService.INSTANCE);
    Assert.assertNotEquals(ApiService.INSTANCE, dto.getName());
    Assert.assertEquals(dto.getName(), ApiService.UPDATED_PREFIX + ApiService.INSTANCE.getName());
  }
}
