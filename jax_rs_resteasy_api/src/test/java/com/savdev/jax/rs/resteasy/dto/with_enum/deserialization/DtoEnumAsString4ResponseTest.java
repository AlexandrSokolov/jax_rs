package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization;

import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;
import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto.DtoEnumAsString4Response;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.with_enum.serialization.DtoEnumAsString4RequestTest.ENUM_AS_STRING_FILE;

public class DtoEnumAsString4ResponseTest {

  /**
   * Default behaviour
   * @throws Exception
   */
  @Test
  public void testFromString2Dto4Response() throws Exception {
    DtoEnumAsString4Response dto = new DtoEnumAsString4Response();
    dto.setAsString(DistanceEnumAsPlainJava.METER);

    Assert.assertEquals(
      dto,
      OBJECT_MAPPER.readValue(
        DtoEnumAsString4ResponseTest.class.getResourceAsStream(ENUM_AS_STRING_FILE),
        DtoEnumAsString4Response.class));
  }
}
