package com.api.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DoubleSerializer
  extends JsonSerializer<Double>
{
  public void serialize(Double value, JsonGenerator jgen, SerializerProvider arg2)
    throws IOException, JsonProcessingException
  {
    DecimalFormat df = new DecimalFormat("0.00");
    String format = df.format(value);
    jgen.writeString(format);
  }
}
