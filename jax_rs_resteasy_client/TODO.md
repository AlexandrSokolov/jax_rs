Test:

- write README.md based on tests
- package documentation with examples
- docs generation, which are visible in git
  swagger
https://github.com/docker/labs/blob/master/developer-tools/java/chapters/ch02-basic-concepts.adoc

- wildfly tests
- auth with signature
  [Everything About HTTPS and SSL (Java)](https://dzone.com/articles/ssl-in-java)
  [Use Client Certificate Authentication With Java and RestTemplate](https://dzone.com/articles/use-client-certificate-authentication-with-java-an)
  [Java mutual SSL authentication / 2-way SSL authentication](https://www.naschenweng.info/2018/02/01/java-mutual-ssl-authentication-2-way-ssl-authentication/)
  [The Java EE 6 Tutorial, HTTPS Client Authentication](https://docs.oracle.com/cd/E19226-01/820-7627/bncbs/index.html)
  [Java HTTPS client certificate authentication](https://stackoverflow.com/questions/1666052/java-https-client-certificate-authentication)
  [Using SSL Authentication in Java Clients](https://docs.oracle.com/cd/E13222_01/wls/docs103/security/SSL_client.html)
  https://avaldes.com/jax-rs-security-using-api-key-for-authorization/
- forwarding from another response
- in tests for server use filter for authentication, but not logic in method, see
  [Securing JAX-RS Endpoints with JWT](https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/)
  

```
      String fileName = program.right().get()._1;
      String url = program.right().get()._2;
      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target = client.target(url);
      final Response r = target.request().get();

      InputStream is = r.readEntity(InputStream.class);
      try {
        byte[] bytes = IOUtils.toByteArray(is);
        ByteArrayInputStream in = new ByteArrayInputStream (bytes);
        return Response
          .fromResponse(r)
          .header("Content-Disposition", String.format("attachment; filename=%s", fileName))
          .entity(in)
          .build();
```          
- wildfly light to start it FROM maven having only war file
- remove deprecated jax-rs based projects
- configure certain calls for filtering, or only partial logging
- debug mode for custom package, not hard-coded: 
  - get stacktrace, 
  - get packages, 
  - check if your package is in debug mode
- debug mode via sse and browser
- [JAX-RS security and Authentication](https://stackoverflow.com/questions/32707448/jax-rs-security-and-authentication)
- [GZIPWriterInterceptor and GZIPReaderInterceptor] (https://gist.github.com/leifoolsen/2daf9749a3d89e9cab97)