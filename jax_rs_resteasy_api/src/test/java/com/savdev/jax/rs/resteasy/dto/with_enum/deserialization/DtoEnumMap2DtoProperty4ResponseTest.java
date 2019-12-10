package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization;

import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto.DtoEnumMapProperty4Response;
import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.enums.DistanceEnum2DtoProperty4Response;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;

public class DtoEnumMap2DtoProperty4ResponseTest {

  public static final String ENUM_AS_STRING_FILE = "/dto/enum.map2property.json";

  @Test
  public void testFromString2Dto4Response() throws Exception {
    DtoEnumMapProperty4Response dto = new DtoEnumMapProperty4Response();
    dto.setEnum2DtoProperty4Response(DistanceEnum2DtoProperty4Response.MILE);

    Assert.assertEquals(
      dto,
      OBJECT_MAPPER.readValue(
        DtoEnumMap2DtoProperty4ResponseTest.class.getResourceAsStream(ENUM_AS_STRING_FILE),
        DtoEnumMapProperty4Response.class));
  }
}
