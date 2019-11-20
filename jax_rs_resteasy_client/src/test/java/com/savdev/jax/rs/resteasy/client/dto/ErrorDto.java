package com.savdev.jax.rs.resteasy.client.dto;

public class ErrorDto {

  public static ErrorDto instance(final String errorDescription){
    ErrorDto dto = new ErrorDto();
    dto.errorDescription = errorDescription;
    return dto;
  }

  String errorDescription;

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }
}
