package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.enums.DistanceEnumAsJsonObject4Response;

import java.util.Objects;

public class DtoEnumAsJsonObject4Response {

  DistanceEnumAsJsonObject4Response asJsonObject;

  public DistanceEnumAsJsonObject4Response getAsJsonObject() {
    return asJsonObject;
  }

  public void setAsJsonObject(DistanceEnumAsJsonObject4Response asJsonObject) {
    this.asJsonObject = asJsonObject;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoEnumAsJsonObject4Response that = (DtoEnumAsJsonObject4Response) o;
    return asJsonObject == that.asJsonObject;
  }

  @Override
  public int hashCode() {
    return Objects.hash(asJsonObject);
  }
}
