package com.isa.java.json.jackson.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CustomDateSerializer extends StdSerializer<Date> {

    protected CustomDateSerializer() {
        this(null);
    }

    protected CustomDateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ssZZZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        jsonGenerator.writeString(dateFormat.format(date));
    }
}
