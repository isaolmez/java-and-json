package com.isa.java.json.jackson.level2.date;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

public class DefaultDateDeserializeTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+04:00"));
        final String dateString = "2017-06-01";

        Date actual = mapper.readValue(dateString, Date.class);


    }
}
