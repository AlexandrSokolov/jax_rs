package com.savdev.jax.rs.resteasy.dto.with_enum.serialization.dto;

import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;

public class DtoEnumCustomSerializer {

  //alternatively we customize custom object as:
  //ObjectMapper mapper = new ObjectMapper();
  //
  //SimpleModule module = new SimpleModule();
  //module.addSerializer(DistanceEnumAsPlainJava.class, new DistanceSerializer());
  //mapper.registerModule(module);

  //or via annotation:
  //@JsonSerialize(using = DistanceSerializer.class)
  DistanceEnumAsPlainJava enumAsPlainJava;

  public DistanceEnumAsPlainJava getEnumAsPlainJava() {
    return enumAsPlainJava;
  }

  public void setEnumAsPlainJava(DistanceEnumAsPlainJava enumAsPlainJava) {
    this.enumAsPlainJava = enumAsPlainJava;
  }
}
