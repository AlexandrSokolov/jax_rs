package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumSpecificField;

import java.util.Objects;

public class DtoEnumSpecificField4Response {

  DistanceEnumSpecificField enumSpecificField;


  public DistanceEnumSpecificField getEnumSpecificField() {
    return enumSpecificField;
  }

  public void setEnumSpecificField(DistanceEnumSpecificField enumSpecificField) {
    this.enumSpecificField = enumSpecificField;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoEnumSpecificField4Response that = (DtoEnumSpecificField4Response) o;
    return enumSpecificField == that.enumSpecificField;
  }

  @Override
  public int hashCode() {
    return Objects.hash(enumSpecificField);
  }
}
