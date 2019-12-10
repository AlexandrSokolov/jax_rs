package com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums.DistanceEnumAsJsonString;

public class DtoEnumAsStringWithJson {

  DistanceEnumAsJsonString asString;

  public DistanceEnumAsJsonString getAsString() {
    return asString;
  }

  public void setAsString(DistanceEnumAsJsonString asString) {
    this.asString = asString;
  }
}
