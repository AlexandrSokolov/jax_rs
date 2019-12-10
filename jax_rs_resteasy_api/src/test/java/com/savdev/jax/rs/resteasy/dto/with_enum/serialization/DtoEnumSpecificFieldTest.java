package com.savdev.jax.rs.resteasy.dto.with_enum.serialization;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumSpecificField;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto.DtoEnumSpecificField;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoEnumSpecificFieldTest {

  public static final String ENUM_SPECIFIC_FIELD_FILE = "/dto/enum.specific.field.json";

  @Test
  public void testEnumAsJsonObjectRequest(){
    DtoEnumSpecificField dto = new DtoEnumSpecificField();
    dto.setEnumSpecificField(DistanceEnumSpecificField.CENTIMETER);
    Assert.assertEquals(
      readTestFile(ENUM_SPECIFIC_FIELD_FILE, DtoEnumSpecificField.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, dto));
  }


}
