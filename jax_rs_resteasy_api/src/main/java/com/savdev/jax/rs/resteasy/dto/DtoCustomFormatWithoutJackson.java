package com.savdev.jax.rs.resteasy.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DtoCustomFormatWithoutJackson {

  BigDecimal bigDecimalValue;
  Date javaUtilDateValue;

  public BigDecimal getBigDecimalValue() {
    return bigDecimalValue;
  }

  public void setBigDecimalValue(BigDecimal bigDecimalValue) {
    this.bigDecimalValue = bigDecimalValue;
  }

  public Date getJavaUtilDateValue() {
    return javaUtilDateValue;
  }

  public void setJavaUtilDateValue(Date javaUtilDateValue) {
    this.javaUtilDateValue = javaUtilDateValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DtoCustomFormatWithoutJackson that = (DtoCustomFormatWithoutJackson) o;
    Calendar javaUtilDateValueC = Calendar.getInstance();
    javaUtilDateValueC.setTime(javaUtilDateValue);
    Calendar thatJavaUtilDateValueC = Calendar.getInstance();
    thatJavaUtilDateValueC.setTime(that.javaUtilDateValue);

    return
      (bigDecimalValue.compareTo(that.bigDecimalValue)) == 0
        && javaUtilDateValueC.get(Calendar.YEAR) == thatJavaUtilDateValueC.get(Calendar.YEAR)
        && javaUtilDateValueC.get(Calendar.MONTH) == thatJavaUtilDateValueC.get(Calendar.MONTH)
        && javaUtilDateValueC.get(Calendar.DAY_OF_MONTH) == thatJavaUtilDateValueC.get(Calendar.DAY_OF_MONTH);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bigDecimalValue, javaUtilDateValue);
  }
}
