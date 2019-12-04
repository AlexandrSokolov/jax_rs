package com.savdev.jax.rs.resteasy.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  public static final String JSON_FILE = "/dto/dto.all.types.json";
  public static final String CUSTOM_FORMAT_JSON_FILE = "/dto/custom.format.json";

  public static String readTestFile(String relativePath, Class<?> testClass) {
    try {
      String withoutFirstSlash = relativePath.startsWith(File.separator) ?
        relativePath.substring(File.separator.length()) : relativePath;

      URL pathResource = testClass
        .getClassLoader()
        .getResource(withoutFirstSlash);
      if (pathResource == null) {
        throw new IllegalStateException(
          "Could not find resource for the path in test resources: [" + relativePath + "]");
      }
      String absolutePath = new File(pathResource.toURI()).getAbsolutePath();
      byte[] encoded = Files.readAllBytes(Paths.get(absolutePath));
      return new String(encoded, StandardCharsets.UTF_8);
    } catch (Exception e){
      throw new IllegalStateException(e);
    }
  }

  public static ObjectMapper customizedObjectMapper(){
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    module.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>(){
      @Override
      public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider serializerProvider)
        throws IOException {
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
    });

    module.addSerializer(Date.class, new JsonSerializer<Date>() {
      @Override
      public void serialize(Date date, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeString(new SimpleDateFormat("yyyy-MM-dd").format(date));
      }
    });

    objectMapper.registerModule(module);
    return objectMapper;
  }
}
