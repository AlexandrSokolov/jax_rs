package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization;

import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumSpecificField;
import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto.DtoEnumSpecificField4Response;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.with_enum.serialization.DtoEnumSpecificFieldTest.ENUM_SPECIFIC_FIELD_FILE;

public class DtoEnumSpecificField4ResponseTest {

  /**
   * Default behaviour
   *
   * */
  @Test
  public void testFromString2Dto4Response() throws Exception {
    DtoEnumSpecificField4Response dto = new DtoEnumSpecificField4Response();
    dto.setEnumSpecificField(DistanceEnumSpecificField.CENTIMETER);

    Assert.assertEquals(
      dto,
      OBJECT_MAPPER.readValue(
        DtoEnumSpecificField4ResponseTest.class.getResourceAsStream(ENUM_SPECIFIC_FIELD_FILE),
        DtoEnumSpecificField4Response.class));
  }
}
