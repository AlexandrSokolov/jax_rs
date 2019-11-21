Test:

- auth with signature
  [Java HTTPS client certificate authentication](https://stackoverflow.com/questions/1666052/java-https-client-certificate-authentication)
  https://avaldes.com/jax-rs-security-using-api-key-for-authorization/
- zip response test it
- jax rs form
- write README.md based on tests
- wildfly tests
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
- remove deprecated jax-rs based projects
- configure certain calls for filtering, or only partial logging
- debug mode for custom package, not hard-coded: 
  - get stacktrace, 
  - get packages, 
  - check if your package is in debug mode
- debug mode via sse and browser
- [JAX-RS security and Authentication](https://stackoverflow.com/questions/32707448/jax-rs-security-and-authentication)
