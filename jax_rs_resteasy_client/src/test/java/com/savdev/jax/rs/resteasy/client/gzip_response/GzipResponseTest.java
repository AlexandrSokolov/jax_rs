package com.savdev.jax.rs.resteasy.client.gzip_response;

import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.gzip_response.api.GzipApi;
import com.savdev.jax.rs.resteasy.client.gzip_response.service.GzipApiService;
import org.jboss.resteasy.plugins.interceptors.GZIPEncodingInterceptor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

public class GzipResponseTest extends JaxRsProxyConfigBaseTest
{

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singleton(GzipApiService.class),
      Collections.singleton(GZIPEncodingInterceptor.class));
  }

  @Test
  public void testGzipGet(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto dto = proxyConfig.proxy(GzipApi.class).getDto();
    Assert.assertEquals(GzipApiService.INSTANCE, dto);
  }

}
