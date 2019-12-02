package com.savdev.jax.rs.resteasy.client.auth_basic;

import com.savdev.jax.rs.resteasy.client.ClientRequestFilterFactory;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import static com.savdev.jax.rs.resteasy.client.auth_basic.AuthBasicService.LOGIN;
import static com.savdev.jax.rs.resteasy.client.auth_basic.AuthBasicService.PASSWORD;

public class AuthBasicTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(AuthBasicService.class));
  }

  @Test
  public void testBasicAuthWithFactory(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterAuthSupplier(() -> ClientRequestFilterFactory.basicAuthentication(
        LOGIN, PASSWORD));
    RestDto dto = proxyConfig.proxy(AuthBasicApi.class).getDto();
    Assert.assertEquals(AuthBasicService.AB_INSTANCE, dto);
  }

  @Test
  public void testBasicAuthWithSupplier(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterAuthSupplier(() ->
        (requestContext) -> requestContext.getHeaders().putSingle(
          HttpHeaders.AUTHORIZATION,
          Base64.getEncoder().encodeToString(
            String.format("%s:%s", LOGIN, PASSWORD)
              .getBytes(StandardCharsets.UTF_8.name()))));
    RestDto dto = proxyConfig.proxy(AuthBasicApi.class).getDto();
    Assert.assertEquals(AuthBasicService.AB_INSTANCE, dto);
  }



}
