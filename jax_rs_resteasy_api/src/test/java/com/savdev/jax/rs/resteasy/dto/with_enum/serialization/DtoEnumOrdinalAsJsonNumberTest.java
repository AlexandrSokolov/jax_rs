package com.savdev.jax.rs.resteasy.dto.with_enum.serialization;

import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto.DtoEnumOrdinalAsJsonNumber;
import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums.DistanceEnumOrdinalAsJsonNumber;
import org.junit.Assert;
import org.junit.Test;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoEnumOrdinalAsJsonNumberTest {

  public static final String ENUM_ORDINAL_AS_JSON_NUMBER_FILE = "dto/enum.ordinal.as.json.number.json";

  @Test
  public void testEnumAsJsonObjectRequest(){
    DtoEnumOrdinalAsJsonNumber dto = new DtoEnumOrdinalAsJsonNumber();
    dto.setEnumOrdinalAsJsonNumber(DistanceEnumOrdinalAsJsonNumber.INCH);
    Assert.assertEquals(
      readTestFile(ENUM_ORDINAL_AS_JSON_NUMBER_FILE, DtoEnumOrdinalAsJsonNumber.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, dto));
  }


}
