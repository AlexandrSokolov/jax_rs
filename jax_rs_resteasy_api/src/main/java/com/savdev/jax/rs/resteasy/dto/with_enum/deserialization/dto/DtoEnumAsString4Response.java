package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;

import java.util.Objects;

public class DtoEnumAsString4Response {

  DistanceEnumAsPlainJava asString;

  public DistanceEnumAsPlainJava getAsString() {
    return asString;
  }

  public void setAsString(DistanceEnumAsPlainJava asString) {
    this.asString = asString;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoEnumAsString4Response that = (DtoEnumAsString4Response) o;
    return asString == that.asString;
  }

  @Override
  public int hashCode() {
    return Objects.hash(asString);
  }
}
