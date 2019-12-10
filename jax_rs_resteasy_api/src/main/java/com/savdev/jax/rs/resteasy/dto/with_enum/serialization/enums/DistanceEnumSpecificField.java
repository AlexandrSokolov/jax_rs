package com.savdev.jax.rs.resteasy.dto.with_enum.serialization.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * we use @JsonValue to specify which getter should be used for serialization
 */
public enum DistanceEnumSpecificField {

  KILOMETER("km", 1000),
  MILE("miles", 1609.34),
  METER("meters", 1),
  INCH("inches", 0.0254),
  CENTIMETER("cm", 0.01),
  MILLIMETER("mm", 0.001);

  private final String unit;
  private final double meters;

  private DistanceEnumSpecificField(String unit, double meters) {
    this.unit = unit;
    this.meters = meters;
  }

  @JsonValue
  public String getUnit() {
    return unit;
  }

  public double getMeters() {
    return meters;
  }
}
