package com.savdev.jax.rs.resteasy.client;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.jax.rs.resteasy.client.filter.ErrorHandlingFilter;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseLogFilter;
import com.savdev.jax.rs.resteasy.client.jackson.JacksonProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class JaxRsProxyConfig {

  public static final String DOMAIN_NOT_SET = "Cannot create proxy. Domain is not specified.";
  public static final String DOMAIN_WRONG = "Cannot create proxy. Domain '%s' is not a correct url. " +
    "Please make sure it contains a correct protocol like 'http' (or other valid ones).";

  private final static UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

  private final JacksonProvider jacksonProvider = JacksonProvider.instance();

  private String domain;

  private Supplier<ClientRequestFilter> authFilterSupplier;

  private Iterable<ClientResponseFilter> clientResponseFilters;

  public static JaxRsProxyConfig instance(String domain){
    return new JaxRsProxyConfig(domain);
  }

  public static JaxRsProxyConfig instance(){
    return new JaxRsProxyConfig();
  }

  public JaxRsProxyConfig domain(String domain){
    if (!urlValidator.isValid(domain)){
      throw new IllegalStateException(String.format(DOMAIN_WRONG, domain));
    }
    this.domain = domain;
    return this;
  }

  public JaxRsProxyConfig clientResponseFilters(
    final Iterable<ClientResponseFilter> clientRequestFilters){
    this.clientResponseFilters = clientRequestFilters;
    return this;
  }

  public JaxRsProxyConfig clientRequestFilterAuthSupplier(
    final Supplier<ClientRequestFilter> authFilterSupplier){
    this.authFilterSupplier = authFilterSupplier;
    return this;
  }

  public JaxRsProxyConfig objectMapperConsumer(
    final Consumer<ObjectMapper> objectMapperConsumer){
    objectMapperConsumer.accept(
      jacksonProvider.objectMapper());
    return this;
  }

  public <T> JaxRsProxyConfig addDeserializer(Function<String, T> transformer, Class<T> type){
    jacksonProvider.simpleModule().addDeserializer(type, new JsonDeserializer<T>() {
      @Override
      public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
          return transformer.apply(
            jsonParser.getValueAsString());
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
      }
    });
    jacksonProvider.objectMapper()
      .registerModule(
        jacksonProvider.simpleModule());
    return this;
  }

  public <T> JaxRsProxyConfig addSerializer(Function<T, String> transformer, Class<T> type){
    jacksonProvider.simpleModule().addSerializer(type, new JsonSerializer<T>() {
      @Override
      public void serialize(T value, JsonGenerator jgen, SerializerProvider serializers) {
        try {
          jgen.writeString(transformer.apply(value));
        } catch (IOException e) {
          throw new IllegalStateException("Could not stringify with a transformer: " + value
            + ". Reason: " + e.getMessage());
        }
      }
    });

    jacksonProvider.objectMapper()
      .registerModule(
        jacksonProvider.simpleModule());
    return this;
  }

  private JaxRsProxyConfig(String domain) {
    if (!urlValidator.isValid(domain)){
      throw new IllegalStateException(String.format(DOMAIN_WRONG, domain));
    }
    this.domain = domain;
  }

  private JaxRsProxyConfig() {}


  public <T> T proxy(Class<T> restProxy){
    if (StringUtils.isEmpty(this.domain)){
      throw new IllegalStateException("Cannot create proxy. Domain is not specified.");
    }

    try {
      Client client = ClientBuilder.newClient();
      client.register(jacksonProvider);
      ResteasyWebTarget target = (ResteasyWebTarget) client.target(domain);

      if (authFilterSupplier != null) {
        target.register(authFilterSupplier.get());
      }
      if (clientResponseFilters != null){
        clientResponseFilters.forEach(target::register);
      }
      target.register(ErrorHandlingFilter.class);
      target.register(RequestResponseLogFilter.class);
      //proxy is returned, but not request is not sent yet
      return target.proxy(restProxy);
    } catch (Exception e){
      throw new IllegalStateException("Could not create proxy for the rest interface: '"
        + restProxy.getName() + "'. Reason: '" + e.getMessage()
        + "'. Exception type: " + e.getClass().getCanonicalName());
    }
  }
}
