package com.savdev.jax.rs.resteasy.dto.with_enum.serialization;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto.DtoEnumAsString;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto.DtoEnumAsStringWithJson;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums.DistanceEnumAsJsonString;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoEnumAsString4RequestTest {

  public static final String ENUM_AS_STRING_FILE = "/dto/enum.as.string.json";

  @Test
  public void testEnumAsStringRequest(){
    DtoEnumAsString dto = new DtoEnumAsString();
    dto.setAsString(DistanceEnumAsPlainJava.METER);
    Assert.assertEquals(
      readTestFile(ENUM_AS_STRING_FILE, DtoEnumAsString.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, dto));
  }

  @Test
  public void testEnumAsStringViaJsonStringRequest(){
    DtoEnumAsStringWithJson dto = new DtoEnumAsStringWithJson();
    dto.setAsString(DistanceEnumAsJsonString.METER);
    Assert.assertEquals(
      readTestFile(ENUM_AS_STRING_FILE, DtoEnumAsStringWithJson.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, dto));
  }
}
