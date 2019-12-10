package com.savdev.jax.rs.resteasy.dto.with_enum.deserialization.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * By using JsonProperty annotation, we are simply telling Jackson to
 * map the value of the @JsonProperty to the object annotated with this value.
 */
public enum DistanceEnum2DtoProperty4Response {

  @JsonProperty("distance-in-km")
  KILOMETER("km", 1000),

  @JsonProperty("distance-in-miles")
  MILE("miles", 1609.34);

  private String unit;
  private final double meters;

  private DistanceEnum2DtoProperty4Response(String unit, double meters) {
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
