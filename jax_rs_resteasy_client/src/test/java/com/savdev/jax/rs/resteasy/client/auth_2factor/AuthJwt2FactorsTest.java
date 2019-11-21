package com.savdev.jax.rs.resteasy.client.auth_2factor;

import com.google.common.collect.Lists;
import com.savdev.jax.rs.resteasy.client.ClientRequestFilterFactory;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.auth_2factor.api.AuthJwtApi;
import com.savdev.jax.rs.resteasy.client.auth_2factor.api.ClientApi;
import com.savdev.jax.rs.resteasy.client.auth_2factor.service.AuthJwtService;
import com.savdev.jax.rs.resteasy.client.auth_2factor.service.ClientWithAuthJwtCheckService;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ClientRequestFilter;

import static com.savdev.jax.rs.resteasy.client.ClientRequestFilterFactory.jwtThroughBasicAuthentication;
import static com.savdev.jax.rs.resteasy.client.auth_2factor.service.AuthJwtService.JWT_LOGIN;
import static com.savdev.jax.rs.resteasy.client.auth_2factor.service.AuthJwtService.JWT_PASSWORD;

public class AuthJwt2FactorsTest extends JaxRsProxyConfigBaseTest {

  /**
   * Cached version
   * @param domain
   * @param user
   * @param password
   * @return
   */
  private static ClientRequestFilter jwtPasswordBasedWithCache(
    String domain,
    String user,
    String password){
    return ClientRequestFilterFactory.jwt2factorCachedAuthentication(
      user,
      JaxRsProxyConfig
        .instance(domain)
        .clientRequestFilterSupplier(() -> ClientRequestFilterFactory.basicAuthentication(user, password)),
      AuthJwtApi.class,
      authJwtService -> authJwtService.authenticate().getJwtToken());
  };

  private static ClientRequestFilter jwtPasswordBasedWithoutCache(
    String domain,
    String user,
    String password){
    return ClientRequestFilterFactory.jwt2factorAuthentication(
      JaxRsProxyConfig
        .instance(domain)
        .clientRequestFilterSupplier(() -> ClientRequestFilterFactory.basicAuthentication(user, password)),
      AuthJwtApi.class,
      authJwtService -> authJwtService.authenticate().getJwtToken());
  };

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(Lists.newArrayList(
      AuthJwtService.class,
      ClientWithAuthJwtCheckService.class));
  }

  /**
   * In the log, you'll see that a request to Auth service at ${AUTH_JWT_PATH}: is sent only once!
   */
  @Test
  public void testJwtThroughBasicAuthentication(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterSupplier(() -> jwtThroughBasicAuthentication(
        URI.toString(),
        JWT_LOGIN,
        JWT_PASSWORD,
        AuthJwtApi.class,
        authJwtService -> authJwtService.authenticate().getJwtToken()));
    //fist a request to auth at: ${AUTH_JWT_PATH}
    //then a requst to ${CLIENT_API_PATH}
    RestDto dto = proxyConfig.proxy(ClientApi.class).getDto();
    Assert.assertEquals(ClientWithAuthJwtCheckService.JWT_INSTANCE, dto);
    //auth is cached. so a request to auth is NOT sent to: ${AUTH_JWT_PATH}
    //only a request to ${CLIENT_API_PATH} is sent
    RestDto dto2 = proxyConfig.proxy(ClientApi.class).getDto();
    Assert.assertEquals(ClientWithAuthJwtCheckService.JWT_INSTANCE, dto2);
  }

  /**
   * In the log, you'll see that a request to Auth service at ${AUTH_JWT_PATH}: is sent only once!
   */
  @Test
  public void testJwt2FactorAuthWithCache(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterSupplier(() -> jwtPasswordBasedWithCache(URI.toString(), JWT_LOGIN, JWT_PASSWORD));
    //fist a request to auth at: ${AUTH_JWT_PATH}
    //then a requst to ${CLIENT_API_PATH}
    RestDto dto = proxyConfig.proxy(ClientApi.class).getDto();
    Assert.assertEquals(ClientWithAuthJwtCheckService.JWT_INSTANCE, dto);
    //auth is cached. so a request to auth is NOT sent to: ${AUTH_JWT_PATH}
    //only a request to ${CLIENT_API_PATH} is sent
    RestDto dto2 = proxyConfig.proxy(ClientApi.class).getDto();
    Assert.assertEquals(ClientWithAuthJwtCheckService.JWT_INSTANCE, dto2);
  }

  /**
   * In the log, you'll see that a request to Auth service at ${AUTH_JWT_PATH}: will be sent twice!
   */
  @Test
  public void testJwt2FactorAuthWithoutCache(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterSupplier(() -> jwtPasswordBasedWithoutCache(URI.toString(), JWT_LOGIN, JWT_PASSWORD));
    //fist a request to auth at: ${AUTH_JWT_PATH}
    //then a requst to ${CLIENT_API_PATH}
    RestDto dto = proxyConfig.proxy(ClientApi.class).getDto();
    Assert.assertEquals(ClientWithAuthJwtCheckService.JWT_INSTANCE, dto);
    //auth is not cached. so again a request to auth at: ${AUTH_JWT_PATH}
    //then a request to ${CLIENT_API_PATH}
    RestDto dto2 = proxyConfig.proxy(ClientApi.class).getDto();
    Assert.assertEquals(ClientWithAuthJwtCheckService.JWT_INSTANCE, dto2);
  }

  /**
   * Here we try to use basic auth only, but it is not enough for the server
   */
  @Test
  public void testJwt2FactorAuthError(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
        .clientRequestFilterSupplier(() -> ClientRequestFilterFactory.basicAuthentication(JWT_LOGIN, JWT_PASSWORD));
      proxyConfig.proxy(ClientApi.class).getDto();
      Assert.fail("'Not authenticated' error expected");
    } catch (Exception e){
      Assert.assertTrue(e.getMessage().contains("HTTP 500 Internal Server Error"));
    }
  }
}
