package com.savdev.jax.rs.resteasy.dto.with_enum.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto.DtoEnumCustomSerializer;
import com.savdev.jax.rs.resteasy.serializer.DistanceEnumSerializer;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoEnumWithCustomSerializerTest {

  public static final String ENUM_CUSTOM_SERIALIZER_FILE = "dto/enum.custom.serializer.json";

  @Test
  public void testEnumAsJsonObjectRequest(){


    DtoEnumCustomSerializer dto = new DtoEnumCustomSerializer();
    dto.setEnumAsPlainJava(DistanceEnumAsPlainJava.KILOMETER);

    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(DistanceEnumAsPlainJava.class, new DistanceEnumSerializer());
    mapper.registerModule(module);

    Assert.assertEquals(
      readTestFile(ENUM_CUSTOM_SERIALIZER_FILE, DtoEnumCustomSerializer.class),
      RequestResponseInfo.stringify(mapper, dto));
  }


}
