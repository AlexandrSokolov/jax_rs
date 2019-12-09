package com.savdev.jax.rs.resteasy.dto.with.jackson.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class DtoConstructor4ResponseWithJackson {

  private String stringValue;

  private int intValue;
  private long longValue;

  private float floatValue;
  private double doubleValue;
  private BigDecimal bigDecimalValue;

  private Character charObjectValue;
  private char charPrimitiveValue;

  private Date javaDateValue;
  private java.sql.Date sqlDateValue;

  private boolean booleanPrimitiveValue;
  private Boolean booleanObjectValue;


  public DtoConstructor4ResponseWithJackson(
    @JsonProperty("stringValue") String stringValue,
    @JsonProperty("intValue") int intValue,
    @JsonProperty("longValue") long longValue,
    @JsonProperty("floatValue") float floatValue,
    @JsonProperty("doubleValue") double doubleValue,
    @JsonProperty("bigDecimalValue") BigDecimal bigDecimalValue,
    @JsonProperty("charObjectValue") Character charObjectValue,
    @JsonProperty("charPrimitiveValue") char charPrimitiveValue,
    @JsonProperty("javaDateValue") Date javaDateValue,
    @JsonProperty("sqlDateValue") java.sql.Date sqlDateValue,
    @JsonProperty("booleanPrimitiveValue") boolean booleanPrimitiveValue,
    @JsonProperty("booleanObjectValue") Boolean booleanObjectValue) {
    this.stringValue = stringValue;
    this.intValue = intValue;
    this.longValue = longValue;
    this.floatValue = floatValue;
    this.doubleValue = doubleValue;
    this.bigDecimalValue = bigDecimalValue;
    this.charObjectValue = charObjectValue;
    this.charPrimitiveValue = charPrimitiveValue;
    this.javaDateValue = javaDateValue;
    this.sqlDateValue = sqlDateValue;
    this.booleanPrimitiveValue = booleanPrimitiveValue;
    this.booleanObjectValue = booleanObjectValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoConstructor4ResponseWithJackson that = (DtoConstructor4ResponseWithJackson) o;
    return intValue == that.intValue &&
      longValue == that.longValue &&
      Float.compare(that.floatValue, floatValue) == 0 &&
      Double.compare(that.doubleValue, doubleValue) == 0 &&
      charPrimitiveValue == that.charPrimitiveValue &&
      booleanPrimitiveValue == that.booleanPrimitiveValue &&
      Objects.equals(stringValue, that.stringValue) &&
      Objects.equals(bigDecimalValue, that.bigDecimalValue) &&
      Objects.equals(charObjectValue, that.charObjectValue) &&
      Objects.equals(javaDateValue, that.javaDateValue) &&
      Objects.equals(sqlDateValue, that.sqlDateValue) &&
      Objects.equals(booleanObjectValue, that.booleanObjectValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      stringValue,
      intValue,
      longValue,
      floatValue,
      doubleValue,
      bigDecimalValue,
      charObjectValue,
      charPrimitiveValue,
      javaDateValue,
      sqlDateValue,
      booleanPrimitiveValue,
      booleanObjectValue);
  }
}
