# document docs:
http://localhost:8080/appContext/appPath/openapi.json
http://localhost:8080/appContext/appPath/openapi.yaml
# jetty with wildfly
# test with wildfly/arquillian
# jetty debug mode
# jetty integration tests:
https://github.com/swagger-api/swagger-samples/blob/2.0/java/java-resteasy-configfile/pom.xml
# swagger without authentication:
https://stackoverflow.com/questions/37671125/how-to-configure-spring-security-to-allow-swagger-url-to-be-accessed-without-aut/37683455#37683455


1. configuration
2. get rest interface
3. integration test with arquillian
4. test with jetty
https://github.com/swagger-api/swagger-samples/tree/2.0/java/java-resteasy-configfile
https://docs.jboss.org/weld/reference/3.0.0.CR2/en-US/html/gettingstarted.html#_deploying_to_jetty
4. junit test
5. serialization/deserialation
- server filters
  https://dzone.com/articles/how-compress-responses-java
-. error processing
- custom serialiation
- information about context!
- [Best practice for REST token-based authentication with JAX-RS and Jersey](https://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey)
- sse
- debug mode via sse and browser
- [The Java EE 6 Tutorial, Form-Based Authentication](https://docs.oracle.com/cd/E19226-01/820-7627/bncbq/index.html)
- [The Java EE 6 Tutorial, HTTP Basic Authentication](https://docs.oracle.com/cd/E19226-01/820-7627/bncbo/index.html)
- [The Java EE 6 Tutorial, Digest Authentication](https://docs.oracle.com/cd/E19226-01/820-7627/bncbw/index.html)
- [The Java EE 6 Tutorial, Working with Security Roles](https://docs.oracle.com/cd/E19226-01/820-7627/bncav/index.html)
- [The Java EE 6 Tutorial, Using Programmatic Security with Web Applications](https://docs.oracle.com/cd/E19226-01/820-7627/gjiie/index.html)
- [The Java EE 6 Tutorial, Using Message Security with Web Applications](https://docs.oracle.com/cd/E19226-01/820-7627/gjiou/index.html)
- [The Java EE 6 Tutorial, Examples: Securing Web Applications](https://docs.oracle.com/cd/E19226-01/820-7627/bncbx/index.html)
- [gzip via resteasy annotation](https://docs.jboss.org/resteasy/docs/2.0.0.GA/userguide/html/gzip.html)
- wildfly tests
- auth with signature
  [Everything About HTTPS and SSL (Java)](https://dzone.com/articles/ssl-in-java)
  [Use Client Certificate Authentication With Java and RestTemplate](https://dzone.com/articles/use-client-certificate-authentication-with-java-an)
  [Java mutual SSL authentication / 2-way SSL authentication](https://www.naschenweng.info/2018/02/01/java-mutual-ssl-authentication-2-way-ssl-authentication/)
  [The Java EE 6 Tutorial, HTTPS Client Authentication](https://docs.oracle.com/cd/E19226-01/820-7627/bncbs/index.html)
  [Java HTTPS client certificate authentication](https://stackoverflow.com/questions/1666052/java-https-client-certificate-authentication)
  [Using SSL Authentication in Java Clients](https://docs.oracle.com/cd/E13222_01/wls/docs103/security/SSL_client.html)
  https://avaldes.com/jax-rs-security-using-api-key-for-authorization/
- wildfly light to start it FROM maven having only war file
- remove deprecated jax-rs based projects  
# caching, options
# http://blog.steveklabnik.com/posts/2011-07-03-nobody-understands-rest-or-http
# http://softwareengineering.stackexchange.com/questions/141410/restful-state-changing-actions
http://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api
http://stackoverflow.com/questions/1619152/how-to-create-rest-urls-without-verbs
https://jira4.brandmaker.com/confluence/display/2DEV/RESTful+API+Design+Guide
