package com.savdev.jax.rs.resteasy.dto.with.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@JsonDeserialize(builder = DtoBuilderWithSettersWithJackson.Builder.class)
public class DtoBuilderWithSettersWithJackson {

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

  private DtoBuilderWithSettersWithJackson(){}

  public static Builder builder(){
    return new Builder();
  }

  public String getStringValue() {
    return stringValue;
  }

  public int getIntValue() {
    return intValue;
  }

  public long getLongValue() {
    return longValue;
  }

  public float getFloatValue() {
    return floatValue;
  }

  public double getDoubleValue() {
    return doubleValue;
  }

  public BigDecimal getBigDecimalValue() {
    return bigDecimalValue;
  }

  public Character getCharObjectValue() {
    return charObjectValue;
  }

  public char getCharPrimitiveValue() {
    return charPrimitiveValue;
  }

  public Date getJavaDateValue() {
    return javaDateValue;
  }

  public java.sql.Date getSqlDateValue() {
    return sqlDateValue;
  }

  public boolean isBooleanPrimitiveValue() {
    return booleanPrimitiveValue;
  }

  public Boolean getBooleanObjectValue() {
    return booleanObjectValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoBuilderWithSettersWithJackson that = (DtoBuilderWithSettersWithJackson) o;
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
    return Objects.hash(stringValue, intValue, longValue, floatValue, doubleValue, bigDecimalValue, charObjectValue, charPrimitiveValue, javaDateValue, sqlDateValue, booleanPrimitiveValue, booleanObjectValue);
  }

  //build - method name is used by default, so we can remove it and set as:
  //@JsonPOJOBuilder(withPrefix = "set")
  @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
  public static class Builder {
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

    public Builder setStringValue(String stringValue) {
      this.stringValue = stringValue;
      return this;
    }

    public Builder setIntValue(int intValue) {
      this.intValue = intValue;
      return this;
    }

    public Builder setLongValue(long longValue) {
      this.longValue = longValue;
      return this;
    }

    public Builder setFloatValue(float floatValue) {
      this.floatValue = floatValue;
      return this;
    }

    public Builder setDoubleValue(double doubleValue) {
      this.doubleValue = doubleValue;
      return this;
    }

    public Builder setBigDecimalValue(BigDecimal bigDecimalValue) {
      this.bigDecimalValue = bigDecimalValue;
      return this;
    }

    public Builder setCharObjectValue(Character charObjectValue) {
      this.charObjectValue = charObjectValue;
      return this;
    }

    public Builder setCharPrimitiveValue(char charPrimitiveValue) {
      this.charPrimitiveValue = charPrimitiveValue;
      return this;
    }

    public Builder setJavaDateValue(Date javaDateValue) {
      this.javaDateValue = javaDateValue;
      return this;
    }

    public Builder setSqlDateValue(java.sql.Date sqlDateValue) {
      this.sqlDateValue = sqlDateValue;
      return this;
    }

    public Builder setBooleanPrimitiveValue(boolean booleanPrimitiveValue) {
      this.booleanPrimitiveValue = booleanPrimitiveValue;
      return this;
    }

    public Builder setBooleanObjectValue(Boolean booleanObjectValue) {
      this.booleanObjectValue = booleanObjectValue;
      return this;
    }

    public DtoBuilderWithSettersWithJackson build() {
      DtoBuilderWithSettersWithJackson dto = new DtoBuilderWithSettersWithJackson();
      dto.stringValue = this.stringValue;
      dto.intValue = this.intValue;
      dto.longValue = this.longValue;
      dto.floatValue = this.floatValue;
      dto.doubleValue = this.doubleValue;
      dto.bigDecimalValue = this.bigDecimalValue;
      dto.charObjectValue = this.charObjectValue;
      dto.charPrimitiveValue = this.charPrimitiveValue;
      dto.javaDateValue = this.javaDateValue;
      dto.sqlDateValue = this.sqlDateValue;
      dto.booleanPrimitiveValue = this.booleanPrimitiveValue;
      dto.booleanObjectValue = this.booleanObjectValue;
      return dto;
    }
  }
}
