package com.savdev.jax.rs.resteasy.client.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JacksonProvider extends JacksonJaxbJsonProvider {

  private ObjectMapper mapper;
  private SimpleModule module = new SimpleModule();

  public static JacksonProvider instance(){
    return new JacksonProvider();
  }

  public JacksonProvider() {
    super();
    this.mapper = _mapperConfig.getConfiguredMapper() != null
      ? _mapperConfig.getConfiguredMapper() : _mapperConfig.getDefaultMapper();
    configureMapper(mapper);

    mapper.registerModule(module);

    module.addSerializer(BigDecimal.class, new MoneySerializer());
  }

  public ObjectMapper objectMapper(){
    return mapper;
  }

  public SimpleModule simpleModule(){
    return this.module;
  }

  private void configureMapper(final ObjectMapper mapper){
    // not default
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
    mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
    mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    mapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);

    // defaults
    mapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
  }
}
