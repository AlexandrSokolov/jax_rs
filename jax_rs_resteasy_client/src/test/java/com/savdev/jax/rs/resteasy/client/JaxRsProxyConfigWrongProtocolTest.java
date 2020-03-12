package com.savdev.jax.rs.resteasy.client;

import org.junit.Assert;
import org.junit.Test;

public class JaxRsProxyConfigWrongProtocolTest {


  public static final String CORRECT_URL = "http://apache.org:18080/test";
  public static final String CORRECT_LOCALHOST_URL = "http://localhost:18080";
  public static final String URL_WITHOUT_PROTOCOL = "localhost:18080/test";

  @Test
  public void testCorrectLocalhostUrlInstance(){
    JaxRsProxyConfig config = JaxRsProxyConfig.instance(CORRECT_LOCALHOST_URL);
    Assert.assertNotNull(config);
  }

  @Test
  public void testCorrectLocalhostUrlDomainSetter(){
    JaxRsProxyConfig config = JaxRsProxyConfig.instance();
    config.domain(CORRECT_LOCALHOST_URL);
    Assert.assertNotNull(config);
  }

  @Test
  public void testCorrectUrlInstance(){
    JaxRsProxyConfig config = JaxRsProxyConfig.instance(CORRECT_URL);
    Assert.assertNotNull(config);
  }

  @Test
  public void testCorrectUrlDomainSetter(){
    JaxRsProxyConfig config = JaxRsProxyConfig.instance();
    config.domain(CORRECT_URL);
    Assert.assertNotNull(config);
  }

  @Test
  public void testWrongUrlInstance(){
    try {
      JaxRsProxyConfig.instance(URL_WITHOUT_PROTOCOL);
      Assert.fail();
    } catch (Exception e){
      Assert.assertEquals(IllegalStateException.class, e.getClass());
      Assert.assertEquals(
        String.format(JaxRsProxyConfig.DOMAIN_WRONG, URL_WITHOUT_PROTOCOL),
        e.getMessage());
    }

  }

  @Test
  public void testWrongUrlDomainSetter(){
    try {
      JaxRsProxyConfig config = JaxRsProxyConfig.instance();
      config.domain(URL_WITHOUT_PROTOCOL);
      Assert.fail();
    } catch (Exception e){
      Assert.assertEquals(IllegalStateException.class, e.getClass());
      Assert.assertEquals(
        String.format(JaxRsProxyConfig.DOMAIN_WRONG, URL_WITHOUT_PROTOCOL),
        e.getMessage());
    }
  }
}
