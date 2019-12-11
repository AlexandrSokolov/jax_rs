package com.savdev.jax.rs.resteasy.client;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo.ERROR_HEADER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JaxRsExceptionsUtilsTest {

  public static final String ERROR_DESCRIPTION = "some text in the body";



  Response response;
  WebApplicationException webApplicationException;

  @Before
  public void init(){
    response = mock(Response.class);
    when(response.readEntity(String.class)).thenReturn(ERROR_DESCRIPTION);
    when(response.getStatus()).thenReturn(Response.Status.NOT_FOUND.getStatusCode());
    MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
    headers.putSingle(ERROR_HEADER, ERROR_DESCRIPTION);
    when(response.getHeaders()).thenReturn(headers);

    webApplicationException = mock(NotFoundException.class);
    when(webApplicationException.getResponse()).thenReturn(response);
  }

  @Test
  public void extractWebException(){
    Assert.assertEquals(
      ERROR_DESCRIPTION,
      JaxRsExceptionsUtils.extractErrorFromResponse(webApplicationException));
  }

  @Test
  public void extractWebExceptionCause1stLevel(){
    Exception fst = new IllegalStateException(webApplicationException);
    Assert.assertEquals(
      ERROR_DESCRIPTION,
      JaxRsExceptionsUtils.extractErrorFromResponse(fst));
  }

  @Test
  public void extractWebExceptionCause2ndLevel(){
    Exception fst = new IllegalStateException(webApplicationException);
    Exception snd = new IllegalStateException(fst);
    Assert.assertEquals(
      ERROR_DESCRIPTION,
      JaxRsExceptionsUtils.extractErrorFromResponse(snd));
  }

  @Test
  public void extractWebExceptionCause3rdLevel(){
    Exception fst = new IllegalStateException(webApplicationException);
    Exception snd = new IllegalStateException(fst);
    Exception thrd = new IllegalStateException(snd);
    Assert.assertEquals(
      ERROR_DESCRIPTION,
      JaxRsExceptionsUtils.extractErrorFromResponse(thrd));
  }

  @Test
  public void extractWebExceptionCauseCyclicReference(){
    Exception fst = new IllegalStateException(ERROR_DESCRIPTION);
    Exception snd = new IllegalStateException(fst);
    //we create cyclic dependency:
    fst.initCause(snd);
    Assert.assertEquals(
      fst.toString(),
      JaxRsExceptionsUtils.extractErrorFromResponse(snd));
  }
}
