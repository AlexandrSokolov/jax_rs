package com.savdev.jax.rs.resteasy.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class MoneySerializer extends JsonSerializer<BigDecimal> {

  @Override
  public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
    // put your desired money style here
    //___________________________________
    // even for integer adds 2 digits: 123 becomes "123.00":
    // jgen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    //___________________________________
    //return 123 if it is nog decimal, returns 123.24 if it is decimal
    // jgen.writeString(new DecimalFormat("#0.##").format(value));
    //____________________________________
    //here we use the 1st option:
    jgen.writeString(value.setScale(4, BigDecimal.ROUND_HALF_UP).toString());
  }
}
