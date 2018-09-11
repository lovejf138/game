package com.api.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDate2Serializer
  extends JsonSerializer<Date>
{
  public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2)
    throws IOException, JsonProcessingException
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(value);
    jgen.writeString(formattedDate);
  }
}
