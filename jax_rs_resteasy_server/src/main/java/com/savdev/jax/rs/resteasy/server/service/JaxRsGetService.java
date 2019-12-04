package com.savdev.jax.rs.resteasy.server.service;


import com.savdev.jax.rs.resteasy.api.JaxRsGet;
import com.savdev.jax.rs.resteasy.dto.DtoGettersSettersWithoutJackson;

public class JaxRsGetService implements JaxRsGet {

  @Override
  public DtoGettersSettersWithoutJackson findById(long id) {
    return DtoGettersSettersWithoutJackson.instance(id,"Some Name");
  }
}
