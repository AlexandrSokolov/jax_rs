package com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums.DistanceEnumAsJsonObject;

public class DtoEnumAsJsonObject {

  DistanceEnumAsJsonObject asJsonObject;

  public DistanceEnumAsJsonObject getAsJsonObject() {
    return asJsonObject;
  }

  public void setAsJsonObject(DistanceEnumAsJsonObject asJsonObject) {
    this.asJsonObject = asJsonObject;
  }
}
