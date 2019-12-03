# Jax Rs Client 

Jax Rs Client simplifies dev and reduces maintenance effort. 

## Features

1. Server error response processing simplification.
2. DTO deserialization from a response error processing simplification.
3. Request/response debugging
4. Marking unknown fields as not mandatory.
5. Money (BigDecimal) format providing.
6. Authentication simplification (basic, 2-factor with caching).
7. Simple way to change a default behaviour providing.

## Usage by client

##### 1. Server error response processing simplification. In case a server returns 4xx or 5xx response code, in the log file we always see all information about the request and the response data.
![Error Message][response error]

You can get this error in the exception handler as:
```
    } catch (Exception e){
      String requestResponseInfo = JaxRsExceptionsUtils.extractErrorFromResponse(e);
      ...
``` 
See [JaxRsProxyConfigErrorTest#testNotFoundWithTextResponseBodyViaJaxRsExceptionsUtils](src/test/java/com/savdev/jax/rs/resteasy/client/error/JaxRsProxyConfigErrorTest.java)

##### 2. Having a DTO deserialization error from a response, we can get all information about request/response.
In case you want to see, how response looks like, you can use the solution, described above:
```
    } catch (Exception e){
      String requestResponseInfo = JaxRsExceptionsUtils.extractErrorFromResponse(e);
      ...
``` 

##### 3. Request/response debugging. We can always enable debug mode per:
* per component, that creates the client
```
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientResponseFilters(Collections.singleton((requestContext, responseContext) -> {
        if (logger.isDebugEnabled()) {
          logger.debug(RequestResponseInfo.instance(requestContext, responseContext));
        }
      }));
```
See [CustomDebugTest](src/test/java/com/savdev/jax/rs/resteasy/client/custom_debug_mode/CustomDebugTest.java)
* per all components, that use the client (enable debug for `com.savdev.jax.rs.resteasy.client.filter`)
In the log, you'll get:
![Debug Message][debug message]
* per all requests (enable debug for `org.apache.http`)
![Debug messages per each apache http request][all requests shown]

##### 4. If server sends us a new field, it is not marked as an error. If you want to override this behaviour, create it as:
```
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
        .objectMapperConsumer(om -> om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true));
```
See [CustomOmUnknownPropertiesTest](src/test/java/com/savdev/jax/rs/resteasy/client/custom_object_mapping/CustomOmUnknownPropertiesTest.java)

##### 5. Java BigDecimal type is sent as "dddd.dd".
 
For instance 123 becomes "123.00". If you want to override it, create a custom serializer. See below an example.

##### 6. Authentication. It is implemented through providing factory methods for ClientRequestFilter. At this moment the following types of authentication supported:
* Basic authentication:
```
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterAuthSupplier(() -> ClientRequestFilterFactory.basicAuthentication(
        LOGIN, PASSWORD));
```
See [AuthBasicTest](src/test/java/com/savdev/jax/rs/resteasy/client/auth_basic/AuthBasicTest.java)

* Two-factor authentication. First we get auth token via basic authenticaton, then, we use jwt token:

How it might look for end-user:
```
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .clientRequestFilterAuthSupplier(() -> jwtPasswordBasedWithCache(URI.toString(), JWT_LOGIN, JWT_PASSWORD));
```
How jwtPasswordBasedWithCache could be implemented:
```
  private static ClientRequestFilter jwtPasswordBasedWithCache(
    String domain,
    String user,
    String password){
    return ClientRequestFilterFactory.jwt2factorCachedAuthentication(
      user,
      JaxRsProxyConfig
        .instance(domain)
        .clientRequestFilterAuthSupplier(() -> ClientRequestFilterFactory.basicAuthentication(user, password)),
      AuthJwtApi.class,
      authJwtService -> authJwtService.authenticate().getJwtToken());
  };
```

See [AuthJwt2FactorsTest](src/test/java/com/savdev/jax/rs/resteasy/client/auth_2factor/AuthJwt2FactorsTest.java)

##### 7. You can always adopt the default behaviour for your needs:
Custom serializer and deserializer:
```
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
      .addDeserializer((dateStr) -> {
        Instant instant = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateStr));
        return java.util.Date.from(instant);
      }, Date.class)
      .addSerializer( date ->
          DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(
            ZonedDateTime.ofInstant(date.toInstant(), TimeZone.getDefault().toZoneId())), Date.class);
```
See [CustomSerializationTest](src/test/java/com/savdev/jax/rs/resteasy/client/custom_serialization/CustomSerializationTest.java)

Custom object mapper:
```
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.instance(URI.toString())
        .objectMapperConsumer(om -> om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true));
```
See [CustomOmUnknownPropertiesTest](src/test/java/com/savdev/jax/rs/resteasy/client/custom_object_mapping/CustomOmUnknownPropertiesTest.java)


## Usage by REST API provider:

REST API provider can deliver partly configured JaxRsProxyConfig to simplify its usage by its consumers. Here is an example:
Provider creates a factory method without domain specifying as part of REST API:
```
public class ProxyConfigProvider {

  public static JaxRsProxyConfig instance(){
    return JaxRsProxyConfig.instance()
      .addDeserializer((dateStr) -> {
        Instant instant = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateStr));
        return java.util.Date.from(instant);
      }, Date.class)
      .addSerializer( date ->
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(
          ZonedDateTime.ofInstant(date.toInstant(), TimeZone.getDefault().toZoneId())), Date.class)
      .objectMapperConsumer(om -> om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true));
  }
}
``` 
Consumer is responsible only for setting domain and authentication:
```
    JaxRsProxyConfig proxyConfig = ProxyConfigProvider.instance()
      .domain(URI.toString())
      .clientRequestFilterAuthSupplier(() ->
        ClientRequestFilterFactory.basicAuthentication("someLogin", "somePwd"));
```

See [ProvideConfigTest](src/test/java/com/savdev/jax/rs/resteasy/client/provide_config/ProvideConfigTest.java)


[response error]: docs/images/ErrorHandlingFilter.jpg "Server Response Error"
[debug message]: docs/images/RequestResponseLogFilter.jpg "Debug Message"
[all requests shown]: docs/images/org.apache.http.debug.jpg "Debug Message"