package com.savdev.jax.rs.resteasy.client.html_form;

import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

public class HtmlFormTest extends JaxRsProxyConfigBaseTest {

  static final String HTML_FORM_NAME = "html form test name";
  static final BigDecimal HTML_FORM_BIG_DECIMAL = new BigDecimal("125.22");

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(HtmlFormService.class));
  }

  @Test
  public void testPut(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString());
    RestDto dto = proxyConfig.proxy(HtmlFormApi.class).updateDto(HTML_FORM_NAME, HTML_FORM_BIG_DECIMAL);
    Assert.assertEquals(HtmlFormService.UPDATED_PREFIX + HTML_FORM_NAME, dto.getName());
    Assert.assertEquals(HTML_FORM_BIG_DECIMAL, dto.getBigDecimal());
  }
}
