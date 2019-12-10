package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.enums.DistanceEnum2DtoProperty4Response;

import java.util.Objects;

public class DtoEnumMapProperty4Response {

  DistanceEnum2DtoProperty4Response enum2DtoProperty4Response;

  public DistanceEnum2DtoProperty4Response getEnum2DtoProperty4Response() {
    return enum2DtoProperty4Response;
  }

  public void setEnum2DtoProperty4Response(DistanceEnum2DtoProperty4Response enum2DtoProperty4Response) {
    this.enum2DtoProperty4Response = enum2DtoProperty4Response;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoEnumMapProperty4Response that = (DtoEnumMapProperty4Response) o;
    return enum2DtoProperty4Response == that.enum2DtoProperty4Response;
  }

  @Override
  public int hashCode() {
    return Objects.hash(enum2DtoProperty4Response);
  }
}
