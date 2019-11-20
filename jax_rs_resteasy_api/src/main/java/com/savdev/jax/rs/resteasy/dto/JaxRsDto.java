package com.savdev.jax.rs.resteasy.dto;

public class JaxRsDto {

  long id;
  String name;

  public static JaxRsDto instance(
    final long id,
    final String name){
    JaxRsDto dto = new JaxRsDto();
    dto.id = id;
    dto.name = name;
    return dto;
  }
}
