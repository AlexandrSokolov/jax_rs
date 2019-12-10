package com.savdev.jax.rs.resteasy.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;

import java.io.IOException;

public class DistanceEnumSerializer extends JsonSerializer<DistanceEnumAsPlainJava> {

  @Override
  public void serialize(DistanceEnumAsPlainJava distance, JsonGenerator jgen, SerializerProvider serializerProvider)
    throws IOException {
    jgen.writeStartObject();
    jgen.writeFieldName("name");
    jgen.writeString(distance.name());
    jgen.writeFieldName("unit");
    jgen.writeString(distance.getUnit());
    jgen.writeFieldName("meters");
    jgen.writeNumber(distance.getMeters());
    jgen.writeEndObject();
  }
}
