package com.savdev.jax.rs.resteasy.client.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class RestDto {
  String name;
  Date date;
  BigDecimal bigDecimal;

  public static RestDto instance(final String name, final Date date, final BigDecimal bigDecimal){
    RestDto dto = new RestDto();
    dto.setName(name);
    dto.setDate(date);
    dto.setBigDecimal(bigDecimal);
    return dto;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public BigDecimal getBigDecimal() {
    return bigDecimal;
  }

  public void setBigDecimal(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RestDto restDto = (RestDto) o;
    return Objects.equals(name, restDto.name) &&
      Objects.equals(date, restDto.date) &&
      Objects.equals(bigDecimal, restDto.bigDecimal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, date, bigDecimal);
  }
}
