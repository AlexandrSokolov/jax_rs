package com.savdev.jax.rs.resteasy.dto.without.jackson.serialization;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * You are responsible for instance creation.
 * Jackson needs only to extract field values to serialize it into string
 *
 * Without getters you can either set annotation:
 * @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
 *
 * Or update the mapper:
 *
 * ObjectMapper objectMapper = new ObjectMapper();
 * objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
 *
 */
public class DtoWithFactory4RequestWithoutJackson {

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
  
  private DtoWithFactory4RequestWithoutJackson(){
    
  }

  public static DtoWithFactory4RequestWithoutJackson instance(
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

    DtoWithFactory4RequestWithoutJackson dto = new DtoWithFactory4RequestWithoutJackson();
    dto.stringValue = stringValue;
    dto.intValue = intValue;
    dto.longValue = longValue;
    dto.floatValue = floatValue;
    dto.doubleValue = doubleValue;
    dto.bigDecimalValue = bigDecimalValue;
    dto.charObjectValue = charObjectValue;
    dto.charPrimitiveValue = charPrimitiveValue;
    dto.javaDateValue = javaDateValue;
    dto.sqlDateValue = sqlDateValue;
    dto.booleanPrimitiveValue = booleanPrimitiveValue;
    dto.booleanObjectValue = booleanObjectValue;

    return dto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoWithFactory4RequestWithoutJackson that = (DtoWithFactory4RequestWithoutJackson) o;
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
