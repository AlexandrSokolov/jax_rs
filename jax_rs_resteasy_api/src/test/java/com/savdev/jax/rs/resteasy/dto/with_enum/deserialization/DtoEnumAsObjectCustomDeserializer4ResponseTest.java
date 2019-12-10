package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.savdev.jax.rs.resteasy.deserializer.DistanceEnumAsObjectDeserializer;
import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;
import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto.DtoEnumCustomDeserializer4Response;
import org.junit.Assert;
import org.junit.Test;

public class DtoEnumAsObjectCustomDeserializer4ResponseTest {

  static final String ENUM_AS_JSON_OBJECT_FILE_4_RESPONSE = "/dto/enum.as.plain.java.via.json.object.json";

  /**
   * You cannot deserialize plain java enum with jackson, without telling jackson how to create it from a string.
   * So by default, you'll get an exception:
   *
   * @throws Exception
   */
  @Test
  public void testFromString2Dto4Response() throws Exception {
    DtoEnumCustomDeserializer4Response dto = new DtoEnumCustomDeserializer4Response();
    dto.setEnumAsPlainJava(DistanceEnumAsPlainJava.MILLIMETER);

    ObjectMapper mapper = new ObjectMapper();

    SimpleModule module = new SimpleModule();
    module.addDeserializer(DistanceEnumAsPlainJava.class, new DistanceEnumAsObjectDeserializer());
    mapper.registerModule(module);

    Assert.assertEquals(
      dto,
      mapper.readValue(
        DtoEnumAsObjectCustomDeserializer4ResponseTest.class.getResourceAsStream(ENUM_AS_JSON_OBJECT_FILE_4_RESPONSE),
        DtoEnumCustomDeserializer4Response.class));
  }
}
