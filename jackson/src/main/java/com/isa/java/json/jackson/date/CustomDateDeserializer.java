package com.isa.java.json.jackson.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class CustomDateDeserializer extends StdDeserializer<Date> {

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        final String formatWithZone = "yyyy-MM-dd'T'HH:mm:ssXXX";
        final String formatWithoutZone = "yyyy-MM-dd'T'HH:mm:ss";
        final String formatWithoutTime = "yyyy-MM-dd";
        String dateString = parser.getText();
        String sanitized = null;
        if (StringUtils.isNotBlank(dateString)) {
            sanitized = dateString.substring(0, 10);
        }

        try {
            return DateUtils.parseDate(sanitized, formatWithZone, formatWithoutZone, formatWithoutTime);
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse " + dateString);
        }
    }
}
