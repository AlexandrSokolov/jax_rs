package com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

//This JsonFormat annotation helps to serialize (object -> string, but not deserialize):
//Actually you can omit this annotation, cause you'll get it by default
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum DistanceEnumAsJsonString {

  KILOMETER("km", 1000),
  MILE("miles", 1609.34),
  METER("meters", 1),
  INCH("inches", 0.0254),
  CENTIMETER("cm", 0.01),
  MILLIMETER("mm", 0.001);

  private String unit;
  private final double meters;

  private DistanceEnumAsJsonString(String unit, double meters) {
    this.unit = unit;
    this.meters = meters;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public double getMeters() {
    return meters;
  }
}
