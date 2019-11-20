package com.savdev.jax.rs.resteasy.client.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RestDtoExtraProps extends RestDto {

  long lValue;

  public static RestDtoExtraProps instance(
    final String name,
    final Date date,
    final BigDecimal bigDecimal,
    final long lValue){
    RestDtoExtraProps dto = new RestDtoExtraProps();
    dto.setName(name);
    dto.setDate(date);
    dto.setBigDecimal(bigDecimal);
    dto.setlValue(lValue);
    return dto;
  }

  public long getlValue() {
    return lValue;
  }

  public void setlValue(long lValue) {
    this.lValue = lValue;
  }
}
