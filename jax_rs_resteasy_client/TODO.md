Test:

- authentication: with Bearer
- authentication: timeout and refresh, caching
- 2 phases authentication
- zip response TODO
- jax rs form
- write README.md based on tests
- wildfly tests
- auth with signature
- forwarding from another response
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