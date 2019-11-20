package com.savdev.jax.rs.resteasy.client.provide_config.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.savdev.jax.rs.resteasy.client.JaxRsProxyConfig;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class ProxyConfigProvider {

  public static JaxRsProxyConfig instance(){
    return JaxRsProxyConfig.instance()
      .addDeserializer((dateStr) -> {
        Instant instant = Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateStr));
        return java.util.Date.from(instant);
      }, Date.class)
      .addSerializer( date ->
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(
          ZonedDateTime.ofInstant(date.toInstant(), TimeZone.getDefault().toZoneId())), Date.class)
      .objectMapperConsumer(om -> om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true));
  }
}
