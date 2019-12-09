package com.savdev.jax.rs.resteasy.dto.without.jackson.deserialization;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


public class DtoDefaultConstructor4ResponseWithoutJackson {

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

  public DtoDefaultConstructor4ResponseWithoutJackson(){
    //do nothing
  }

  public DtoDefaultConstructor4ResponseWithoutJackson(
    String stringValue,
    int intValue,
    long longValue,
    float floatValue,
    double doubleValue,
    BigDecimal bigDecimalValue,
    Character charObjectValue,
    char charPrimitiveValue,
    Date javaDateValue,
    java.sql.Date sqlDateValue,
    boolean booleanPrimitiveValue,
    Boolean booleanObjectValue) {
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
    DtoDefaultConstructor4ResponseWithoutJackson that = (DtoDefaultConstructor4ResponseWithoutJackson) o;
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
