- enable org.apache.http for the whole system 
- update internal documentation in JaxRs and Wildfly pages
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