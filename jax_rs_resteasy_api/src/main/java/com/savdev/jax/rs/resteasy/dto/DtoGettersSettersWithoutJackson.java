package com.savdev.jax.rs.resteasy.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * No JSON annotations on Java Object
 *
 * Default Object mapper can handle it:
 *  - serialize dto into string for requests and
 *  - deserialize response string into dto
 *
 */
public class DtoGettersSettersWithoutJackson {

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

  public String getStringValue() {
    return stringValue;
  }

  public void setStringValue(String stringValue) {
    this.stringValue = stringValue;
  }

  public int getIntValue() {
    return intValue;
  }

  public void setIntValue(int intValue) {
    this.intValue = intValue;
  }

  public long getLongValue() {
    return longValue;
  }

  public void setLongValue(long longValue) {
    this.longValue = longValue;
  }

  public float getFloatValue() {
    return floatValue;
  }

  public void setFloatValue(float floatValue) {
    this.floatValue = floatValue;
  }

  public double getDoubleValue() {
    return doubleValue;
  }

  public void setDoubleValue(double doubleValue) {
    this.doubleValue = doubleValue;
  }

  public BigDecimal getBigDecimalValue() {
    return bigDecimalValue;
  }

  public void setBigDecimalValue(BigDecimal bigDecimalValue) {
    this.bigDecimalValue = bigDecimalValue;
  }

  public Character getCharObjectValue() {
    return charObjectValue;
  }

  public void setCharObjectValue(Character charObjectValue) {
    this.charObjectValue = charObjectValue;
  }

  public char getCharPrimitiveValue() {
    return charPrimitiveValue;
  }

  public void setCharPrimitiveValue(char charPrimitiveValue) {
    this.charPrimitiveValue = charPrimitiveValue;
  }

  public Date getJavaDateValue() {
    return javaDateValue;
  }

  public void setJavaDateValue(Date javaDateValue) {
    this.javaDateValue = javaDateValue;
  }

  public java.sql.Date getSqlDateValue() {
    return sqlDateValue;
  }

  public void setSqlDateValue(java.sql.Date sqlDateValue) {
    this.sqlDateValue = sqlDateValue;
  }

  public boolean isBooleanPrimitiveValue() {
    return booleanPrimitiveValue;
  }

  public void setBooleanPrimitiveValue(boolean booleanPrimitiveValue) {
    this.booleanPrimitiveValue = booleanPrimitiveValue;
  }

  public Boolean getBooleanObjectValue() {
    return booleanObjectValue;
  }

  public void setBooleanObjectValue(Boolean booleanObjectValue) {
    this.booleanObjectValue = booleanObjectValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoGettersSettersWithoutJackson that = (DtoGettersSettersWithoutJackson) o;
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
