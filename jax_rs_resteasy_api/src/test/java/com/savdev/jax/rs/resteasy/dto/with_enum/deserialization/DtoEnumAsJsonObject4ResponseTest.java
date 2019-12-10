package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization;

import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto.DtoEnumAsJsonObject4Response;
import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.enums.DistanceEnumAsJsonObject4Response;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.with_enum.serialization.DtoEnumAsJsonObjectTest.ENUM_AS_JSON_OBJECT_FILE;

public class DtoEnumAsJsonObject4ResponseTest {

  /**
   * Default behaviour
   * @throws Exception
   */
  @Test
  public void testFromString2Dto4Response() throws Exception {
    DtoEnumAsJsonObject4Response dto = new DtoEnumAsJsonObject4Response();
    dto.setAsJsonObject(DistanceEnumAsJsonObject4Response.MILLIMETER);

    Assert.assertEquals(
      dto,
      OBJECT_MAPPER.readValue(
        DtoEnumAsJsonObject4ResponseTest.class.getResourceAsStream(ENUM_AS_JSON_OBJECT_FILE),
        DtoEnumAsJsonObject4Response.class));
  }
}
