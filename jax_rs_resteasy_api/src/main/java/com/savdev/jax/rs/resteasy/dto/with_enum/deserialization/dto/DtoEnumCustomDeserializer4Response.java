package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;

import java.util.Objects;

public class DtoEnumCustomDeserializer4Response {

  //alternatively we customize custom object as:
  //ObjectMapper mapper = new ObjectMapper();
  //
  //SimpleModule module = new SimpleModule();
  //module.addSerializer(DistanceEnumAsPlainJava.class, new DistanceEnumDeserializer());
  //mapper.registerModule(module);

  //or via annotation:
//  @JsonDeserialize(using = DistanceEnumDeserializer.class)
  DistanceEnumAsPlainJava enumAsPlainJava;

  public DistanceEnumAsPlainJava getEnumAsPlainJava() {
    return enumAsPlainJava;
  }

  public void setEnumAsPlainJava(DistanceEnumAsPlainJava enumAsPlainJava) {
    this.enumAsPlainJava = enumAsPlainJava;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoEnumCustomDeserializer4Response that = (DtoEnumCustomDeserializer4Response) o;
    return enumAsPlainJava == that.enumAsPlainJava;
  }

  @Override
  public int hashCode() {
    return Objects.hash(enumAsPlainJava);
  }
}
