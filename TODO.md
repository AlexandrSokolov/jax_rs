- [RESTful Java with JAX RS 2.0 (Second Edition)] https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/en/index.html
- [](https://legacy.gitbook.com/@dennis-xlc)
- add findbug 
```
            <plugin>
                <!--
                    To run manually: mvn findbugs:gui
                -->
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>findbugs-maven-plugin</artifactId>
                <version>3.0.1</version>
                <configuration>
                    <effort>Max</effort>
                    <threshold>Low</threshold>
                </configuration>
                <executions>
                    <execution>
                        <configuration>
                            <effort>Max</effort>
                            <threshold>Low</threshold>
                        </configuration>
                        <goals>
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>  
```
# # Jackson vs. Jaxb (both Jackson and JAXB annotations) 
# Asyncronious tasks compare them
# Running heavy tasks with update in response
# file downloading
# big file downloading
# async processing
# file as stream downloading
# jax rs forwarding



# features from https://docs.jboss.org/resteasy/docs/3.1.2.Final/userguide/html_single/index.html
https://stackoverflow.com/questions/30568353/how-to-de-serialize-an-immutable-object-without-default-constructor-using-object add to docs
# closable
# pooling

async long running task, see example with Amazon ORder UPdate


tests
https://github.com/resteasy/Resteasy/tree/master/testsuite

example with
@Provider
@Produces("application/vnd.qumu.vcc.v1+json")
@Consumes("application/vnd.qumu.vcc.v1+json")
public class QumuJacksonProvider extends JacksonCustomProvider {
}
# loading tests to see if we must/must not close client
# authentication with signature, via login, without password

-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG -Dorg.apache.commons.logging.simplelog.log.com.brandmaker.si.commons.rest.client.filter=DEBUG
-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG -Dorg.apache.commons.logging.simplelog.log.com.brandmaker.si.commons.rest.client.filter=DEBUG







