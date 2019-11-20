package com.savdev.jax.rs.resteasy.server.service;


import com.savdev.jax.rs.resteasy.api.JaxRsGet;
import com.savdev.jax.rs.resteasy.dto.JaxRsDto;

public class JaxRsGetService implements JaxRsGet {

  @Override
  public JaxRsDto findById(long id) {
    return JaxRsDto.instance(id,"Some Name");
  }
}
