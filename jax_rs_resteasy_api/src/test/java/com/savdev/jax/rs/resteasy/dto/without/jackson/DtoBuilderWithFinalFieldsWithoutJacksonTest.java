package com.savdev.jax.rs.resteasy.dto.without.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.savdev.jax.rs.resteasy.client.filter.RequestResponseInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static com.savdev.jax.rs.resteasy.dto.TestUtils.DATE_FORMAT;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.JSON_FILE;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.OBJECT_MAPPER;
import static com.savdev.jax.rs.resteasy.dto.TestUtils.readTestFile;

public class DtoBuilderWithFinalFieldsWithoutJacksonTest {

  /**
   * Convert object to String and compare with file content from the file
   *
   * This check is usefull for requests, when we need to make sure, that correct values will be sent to server
   *
   * @throws Exception
   */
  @Test
  public void testFromDto2String4Requests() throws Exception {
    Assert.assertEquals(
      readTestFile(JSON_FILE, DtoBuilderWithFinalFieldsWithoutJackson.class),
      RequestResponseInfo.stringify(OBJECT_MAPPER, instance()));
  }

  /**
   * Parse a string from the file using Object Mapper,
   *
   * check the real object is according to what we expect when parse response
   *
   * This check is useful for responds
   *
   * @throws Exception
   */
  @Test
  public void testFromString2Dto4Response() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector(){
      @Override
      public Class<?> findPOJOBuilder(AnnotatedClass ac) {
        //set analogue of: @JsonDeserialize(builder = DtoBuilderWithFinalFieldsWithoutJackson.Builder.class)
        if (DtoBuilderWithFinalFieldsWithoutJackson.class.equals(ac.getRawType())) {
          return DtoBuilderWithFinalFieldsWithoutJackson.Builder.class;
        } else {
          return super.findPOJOBuilder(ac);
        }
      }
      @Override
      public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac) {
        if (DtoBuilderWithFinalFieldsWithoutJackson.class.equals(ac.getRawType())) {
          //both build and with - are default in this case:
          //set analogue of @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
          return new JsonPOJOBuilder.Value("build", "with");
        } else {
          return super.findPOJOBuilderConfig(ac);
        }
      }
    });

    Assert.assertEquals(
      instance(),
      objectMapper.readValue(
        DtoBuilderWithFinalFieldsWithoutJacksonTest.class.getResourceAsStream(JSON_FILE),
        DtoBuilderWithFinalFieldsWithoutJackson.class));
  }

  private DtoBuilderWithFinalFieldsWithoutJackson instance() throws ParseException {
    return DtoBuilderWithFinalFieldsWithoutJackson.builder()
      .withStringValue("test string value")
      .withIntValue(100)
      .withLongValue(200L)
      .withFloatValue(100.05F)
      .withDoubleValue(110.05)
      //do not init it as 220.000777, but as a string "220.000777"
      .withBigDecimalValue(new BigDecimal("220.000777"))
      .withCharPrimitiveValue('a')
      .withCharObjectValue('B')
      .withJavaDateValue(DATE_FORMAT.parse("2020-05-10 12:23:54"))
      .withSqlDateValue(new java.sql.Date(DATE_FORMAT.parse("2020-07-08 10:03:14").getTime()))
      .withBooleanPrimitiveValue(true)
      .withBooleanObjectValue(false)
      .build();
  }
}
