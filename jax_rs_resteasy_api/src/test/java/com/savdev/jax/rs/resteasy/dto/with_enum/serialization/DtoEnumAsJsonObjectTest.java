package com.savdev.jax.rs.resteasy.dto.with_enum.serialization;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto.DtoEnumAsJsonObject;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums.DistanceEnumAsJsonObject;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoEnumAsJsonObjectTest {

  public static final String ENUM_AS_JSON_OBJECT_FILE = "/dto/enum.as.json.object.json";

  @Test
  public void testEnumAsJsonObjectRequest(){
    DtoEnumAsJsonObject dto = new DtoEnumAsJsonObject();
    dto.setAsJsonObject(DistanceEnumAsJsonObject.MILLIMETER);
    Assert.assertEquals(
      readTestFile(ENUM_AS_JSON_OBJECT_FILE, DtoEnumAsJsonObject.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, dto));
  }


}
