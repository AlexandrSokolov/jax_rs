package com.savdev.jax.rs.resteasy.dto.without.jackson;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class DtoBuilderWithFinalFieldsWithoutJackson {

  public final String stringValue;

  public final int intValue;
  public final long longValue;

  public final float floatValue;
  public final double doubleValue;
  public final BigDecimal bigDecimalValue;

  public final Character charObjectValue;
  public final char charPrimitiveValue;

  public final Date javaDateValue;
  public final java.sql.Date sqlDateValue;

  public final boolean booleanPrimitiveValue;
  public final Boolean booleanObjectValue;

  private DtoBuilderWithFinalFieldsWithoutJackson(
    final String stringValue,
    final int intValue,
    final long longValue,
    final float floatValue,
    final double doubleValue,
    final BigDecimal bigDecimalValue,
    final Character charObjectValue,
    final char charPrimitiveValue,
    final Date javaDateValue,
    final java.sql.Date sqlDateValue,
    final boolean booleanPrimitiveValue,
    final Boolean booleanObjectValue) {
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


  public static Builder builder(){
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoBuilderWithFinalFieldsWithoutJackson that = (DtoBuilderWithFinalFieldsWithoutJackson) o;
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

    public Builder withStringValue(String stringValue) {
      this.stringValue = stringValue;
      return this;
    }

    public Builder withIntValue(int intValue) {
      this.intValue = intValue;
      return this;
    }

    public Builder withLongValue(long longValue) {
      this.longValue = longValue;
      return this;
    }

    public Builder withFloatValue(float floatValue) {
      this.floatValue = floatValue;
      return this;
    }

    public Builder withDoubleValue(double doubleValue) {
      this.doubleValue = doubleValue;
      return this;
    }

    public Builder withBigDecimalValue(BigDecimal bigDecimalValue) {
      this.bigDecimalValue = bigDecimalValue;
      return this;
    }

    public Builder withCharObjectValue(Character charObjectValue) {
      this.charObjectValue = charObjectValue;
      return this;
    }

    public Builder withCharPrimitiveValue(char charPrimitiveValue) {
      this.charPrimitiveValue = charPrimitiveValue;
      return this;
    }

    public Builder withJavaDateValue(Date javaDateValue) {
      this.javaDateValue = javaDateValue;
      return this;
    }

    public Builder withSqlDateValue(java.sql.Date sqlDateValue) {
      this.sqlDateValue = sqlDateValue;
      return this;
    }

    public Builder withBooleanPrimitiveValue(boolean booleanPrimitiveValue) {
      this.booleanPrimitiveValue = booleanPrimitiveValue;
      return this;
    }

    public Builder withBooleanObjectValue(Boolean booleanObjectValue) {
      this.booleanObjectValue = booleanObjectValue;
      return this;
    }

    public DtoBuilderWithFinalFieldsWithoutJackson build() {
      return new DtoBuilderWithFinalFieldsWithoutJackson(
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
}
