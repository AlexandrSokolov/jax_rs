package com.savdev.jax.rs.resteasy.client.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savdev.jax.rs.resteasy.client.jackson.JacksonProvider;
import com.savdev.jax.rs.resteasy.plugins.interceptors.GZIPDecodingInterceptor;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class RequestResponseInfo {

  public static final String ERROR_HEADER = "Error Request Response Info";
  public static final String NO_REQUEST_BODY = "NO REQUEST BODY";
  public static final String NO_RESPONSE_BODY = "NO RESPONSE BODY";

  private RequestResponseInfo(
    URI url,
    String httpMethod,
    MultivaluedMap<String, String> requestHeaders,
    ObjectMapper objectMapper,
    Object requestBody,
    int responseStatus,
    MultivaluedMap<String, String> responseHeaders,
    Object responseBody) {
    this.url = url;
    this.httpMethod = httpMethod;
    this.requestHeaders = requestHeaders;
    this.objectMapper = objectMapper;
    this.requestBody = requestBody;
    this.responseStatus = responseStatus;
    this.responseHeaders = responseHeaders;
    this.responseBody = responseBody;
  }

  private URI url;

  private String httpMethod;

  private MultivaluedMap<String, String> requestHeaders ;

  private Object requestBody;

  private int responseStatus;

  private MultivaluedMap<String, String> responseHeaders;

  private Object responseBody;

  //do not create getter for this field!
  private ObjectMapper objectMapper;

  public static RequestResponseInfo instance(
    final ClientRequestContext requestContext,
    final ClientResponseContext responseContext){

    ObjectMapper objectMapper = extractObjectMapper(requestContext);

    return RequestResponseInfo.builder()
      .url(requestContext.getUri())
      .httpMethod(requestContext.getMethod().toUpperCase())
      .requestHeaders(requestContext.getStringHeaders())
      .objectMapper(objectMapper)
      .requestBody(requestContext.getEntity())
      .responseStatus(responseContext.getStatus())
      .responseHeaders(responseContext.getHeaders())
      .responseBody(extractResponseBody(objectMapper, responseContext))
      .build();
  }

  public static Optional<String> errorRequestResponseInfo(final Response response){
    if (response.getHeaders() != null
      && response.getHeaders().containsKey(ERROR_HEADER)) {
      return Optional.of(
        response
          .getHeaders()
          .getFirst(ERROR_HEADER)
          .toString());
    } else {
      return Optional.empty();
    }
  }

  public Object getRequestBody() {
    return requestBody;
  }

  public Object getResponseBody() {
    return responseBody;
  }

  public URI getUrl() {
    return url;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public MultivaluedMap<String, String> getRequestHeaders() {
    return requestHeaders;
  }

  public int getResponseStatus() {
    return responseStatus;
  }

  public MultivaluedMap<String, String> getResponseHeaders() {
    return responseHeaders;
  }

  @Override
  public String toString() {
    try {

      final MultivaluedMap<String, String> filteredRequestHeaders = new MultivaluedHashMap<>();
      this.requestHeaders.entrySet().stream()
        .filter(entry -> !HttpHeaders.AUTHORIZATION.equals(entry.getKey()))
        .forEach(entry -> filteredRequestHeaders.addAll(entry.getKey(), entry.getValue()));

      final MultivaluedMap<String, String> filteredResponseHeaders = new MultivaluedHashMap<>();
      this.responseHeaders.entrySet().stream()
        .filter(entry -> !ERROR_HEADER.equals(entry.getKey()))
        .forEach(entry -> filteredResponseHeaders.addAll(entry.getKey(), entry.getValue()));
      //Stringify original instance without ObjectMapper and custom ERROR_HEADER and AUTHORIZATION header
      return stringify(this.objectMapper, RequestResponseInfo.builder()
        .url(this.url)
        .httpMethod(this.httpMethod)
        .requestHeaders(filteredRequestHeaders)
        .requestBody(this.requestBody)
        .responseStatus(this.responseStatus)
        .responseHeaders(filteredResponseHeaders)
        .responseBody(this.responseBody)
        .build());
    } catch (Exception ignored){
      return new StringJoiner(",\n\t", RequestResponseInfo.class.getSimpleName() + " {", "}")
        .add("\n\tURL = " + url)
        .add("HTTP Method = '" + httpMethod + "'")
        .add("Request Headers = " + requestHeaders.entrySet().stream()
          .filter(entry -> !HttpHeaders.AUTHORIZATION.equals(entry.getKey()))
          .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)))
        .add("Request Body = " +
          ( requestBody == null ? NO_REQUEST_BODY : requestBody ))
        .add("Response Status=" + responseStatus)
        .add("Response Headers=" + responseHeaders
          .entrySet().stream()
          .filter(entry -> !ERROR_HEADER.equals(entry.getKey()))
          .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)))
        .add("Response Body=" +
          ( (responseBody == null ) ? NO_RESPONSE_BODY : responseBody ))
        .toString();
    }

  }

  private static ObjectMapper extractObjectMapper(final ClientRequestContext requestContext) {
    List<JacksonProvider> providers = requestContext.getClient().getConfiguration().getInstances().stream()
      .filter(object -> object instanceof JacksonProvider)
      .map(jacksonProvider -> (JacksonProvider) jacksonProvider)
      .collect(Collectors.toList());
    return  (providers.size() == 1) ? providers.get(0).objectMapper() : new ObjectMapper();
  }

  public static String stringify(
    final ObjectMapper objectMapper,
    final Object object){
    try {
      return objectMapper
        .writerWithDefaultPrettyPrinter()
        .writeValueAsString(object)
        .replaceAll("^\"|\"$", "");
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Could not stringify object: "
        + object.getClass().getCanonicalName()
        + ". Reason: " + e.getMessage(), e);
    }
  }


  private static Object extractResponseBody(
    final ObjectMapper objectMapper, final ClientResponseContext responseContext) {
    try {
      if (responseContext.getLength() != 0) {


        StringBuilder b = new StringBuilder();

        final InputStream fromResponse = responseContext.getEntityStream();
        final Charset responseCharset =
          (responseContext.getMediaType() != null
            && responseContext.getMediaType().getParameters() != null
            && responseContext.getMediaType().getParameters().containsKey("charset")) ?
            Charset.forName(responseContext.getMediaType().getParameters().get("charset"))
            : StandardCharsets.UTF_8;

        if (responseContext.getHeaders() != null
          && responseContext.getHeaders().containsKey("Content-Encoding")
          && responseContext.getHeaders().getFirst("Content-Encoding").equals("gzip")) {

          GZIPDecodingInterceptor.FinishableGZIPInputStream zipped =
            new GZIPDecodingInterceptor.FinishableGZIPInputStream(fromResponse);
          InputStream reusable = logInboundEntity(
            b,
            zipped,
            responseCharset);
          responseContext.setEntityStream(reusable);

          responseContext.getHeaders().remove("Content-Encoding");
        } else {
          InputStream reusable = logInboundEntity(
            b,
            fromResponse,
            responseCharset);
          responseContext.setEntityStream(reusable);
        }
        return bodyObject(objectMapper, b.toString());
      } else {
        return null;
      }
    } catch (Exception e) {
      throw new IllegalStateException("Could not extract information from response body", e);
    }
  }

  private static Object bodyObject(final ObjectMapper objectMapper, final String bodyString){
    final String fixedString = bodyString.endsWith("\n") ? bodyString.substring(0, bodyString.length() - 1)
      : bodyString;
    try {
      return objectMapper.readTree(fixedString);
    } catch (JsonProcessingException e) {
      return fixedString;
    } catch (IOException e) {
      throw new IllegalStateException("Could not extract object from : " + bodyString, e);
    }
  }

  private static final int DEFAULT_MAX_ENTITY_SIZE = 8 * 1024;
  private static InputStream logInboundEntity(
    final StringBuilder b,
    InputStream stream,
    final Charset charset) throws IOException {
    if (!stream.markSupported()) {
      stream = new BufferedInputStream(stream);
    }
    stream.mark(DEFAULT_MAX_ENTITY_SIZE + 1);
    final byte[] entity = new byte[DEFAULT_MAX_ENTITY_SIZE + 1];
    final int entitySize = stream.read(entity);
    if (entitySize != -1) {
      b.append(new String(entity, 0, Math.min(entitySize, DEFAULT_MAX_ENTITY_SIZE), charset));
      if (entitySize > DEFAULT_MAX_ENTITY_SIZE) {
        b.append("...more...");
      }
      b.append('\n');
    }
    stream.reset();
    return stream;
  }

  private static String stringify(Form form){
    String original = "Original form: " + System.lineSeparator()
      + form.asMap().entrySet().stream().map(key2list ->
      key2list.getKey() + "=" + String.join(",", key2list.getValue()))
      .collect(Collectors.joining("&"));
    String encoded = "URL encoded form sent to server: " + System.lineSeparator()
      + form.asMap().entrySet().stream().map(key2list -> {
      try {
        return key2list.getKey() + "=" + URLEncoder.encode(
          String.join(",", key2list.getValue()),
          StandardCharsets.UTF_8.toString());
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }})
      .collect(Collectors.joining("&"));
    return original + System.lineSeparator() + encoded;
  }

  private static RequestResponseInfoBuilder builder(){
    return new RequestResponseInfoBuilder();
  }

  private static class RequestResponseInfoBuilder {
    private URI url;
    private String httpMethod;
    private MultivaluedMap<String, String> requestHeaders;
    private ObjectMapper objectMapper;
    private Object requestBody;
    private int responseStatus;
    private MultivaluedMap<String, String> responseHeaders;
    private Object responseBody;

    private RequestResponseInfoBuilder url(URI url) {
      this.url = url;
      return this;
    }

    private RequestResponseInfoBuilder httpMethod(String httpMethod) {
      this.httpMethod = httpMethod;
      return this;
    }

    private RequestResponseInfoBuilder requestHeaders(MultivaluedMap<String, String> requestHeaders) {
      this.requestHeaders = requestHeaders;
      return this;
    }

    private RequestResponseInfoBuilder requestBody(Object requestBody) {
      this.requestBody = requestBody;
      return this;
    }

    private RequestResponseInfoBuilder responseStatus(int responseStatus) {
      this.responseStatus = responseStatus;
      return this;
    }

    private RequestResponseInfoBuilder responseHeaders(MultivaluedMap<String, String> responseHeaders) {
      this.responseHeaders = responseHeaders;
      return this;
    }

    private RequestResponseInfoBuilder responseBody(Object responseBody) {
      this.responseBody = responseBody;
      return this;
    }

    private RequestResponseInfoBuilder objectMapper(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
      return this;
    }

    private RequestResponseInfo build() {
      return new RequestResponseInfo(
        url,
        httpMethod,
        requestHeaders,
        objectMapper,
        requestBody,
        responseStatus,
        responseHeaders,
        responseBody);
    }
  }
}
