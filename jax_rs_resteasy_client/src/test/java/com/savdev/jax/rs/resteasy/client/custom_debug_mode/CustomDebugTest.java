package com.savdev.jax.rs.resteasy.client.custom_debug_mode;

import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfigBaseTest;
import com.savdev.jax.rs.resteasy.client.dto.RestDto;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

/**
 * In the console, we'll seee debug messages from the CustomDebugTest only, but not from the RequestResponseLogFilter:
 * [DEBUG] CustomDebugTest
 */
public class CustomDebugTest extends JaxRsProxyConfigBaseTest {

  //do not make this field static, you'll not get any debug messages in this case!
  final Log logger = LogFactory.getLog(CustomDebugTest.class);

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Collections.singletonList(CustomDebugService.class));
    //disable logging via RequestResponseLogFilter, configured in the base class
    System.clearProperty("org.apache.commons.logging.simplelog.log.com.savdev.jax.rs.resteasy.client.filter");
    //enable logging for the current test class
    System.setProperty("org.apache.commons.logging.simplelog.log.com.savdev.jax.rs.resteasy.client.custom_debug_mode", "DEBUG");
  }


  @Test
  public void testCustomDebugMode(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientResponseFilters(Collections.singleton((requestContext, responseContext) -> {
        if (logger.isDebugEnabled()) {
          logger.debug(RequestResponseInfo.instance(requestContext, responseContext));
        }
      }));
    RestDto dto = proxyConfig.proxy(CustomDebugApi.class).getDto();
    Assert.assertEquals(CustomDebugService.CD_INSTANCE, dto);
  }
}
