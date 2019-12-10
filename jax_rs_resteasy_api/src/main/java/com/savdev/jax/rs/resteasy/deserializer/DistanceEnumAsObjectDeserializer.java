package com.savdev.jax.rs.resteasy.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.savdev.jax.rs.resteasy.dto.with_enum.DistanceEnumAsPlainJava;

import java.io.IOException;

public class DistanceEnumAsObjectDeserializer extends JsonDeserializer<DistanceEnumAsPlainJava> {

  @Override
  public DistanceEnumAsPlainJava deserialize(
    final JsonParser jsonParser,
    final DeserializationContext ctxt) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    String unit = node.get("unit").asText();
    double meters = node.get("meters").asDouble();

    for (DistanceEnumAsPlainJava distance : DistanceEnumAsPlainJava.values()) {

      if (distance.getUnit().equals(unit) && Double.compare(distance.getMeters(), meters) == 0) {
        return distance;
      }
    }

    return null;
  }
}
