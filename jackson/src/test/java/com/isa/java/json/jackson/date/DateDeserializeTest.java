package com.isa.java.json.jackson.date;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

public class DateDeserializeTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldDeserialize() throws IOException {
        String json = "{\"date\":\"2017-06-08'T'00:00:00+08:00\"}";

        TestClass actual = mapper.readValue(json, TestClass.class);
        
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(actual.getDate());
        assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2017);
        assertThat(calendar.get(Calendar.MONTH)).isEqualTo(5);
        assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(8);
        assertThat(calendar.get(Calendar.HOUR_OF_DAY)).isEqualTo(0);
        assertThat(calendar.get(Calendar.MINUTE)).isEqualTo(0);
        assertThat(calendar.get(Calendar.SECOND)).isEqualTo(0);

    }

    private static class TestClass {

        @JsonDeserialize(using = DateDeserializer.class)
        private Date date;

        public Date getDate() {
            return date;
        }

        @JsonFormat(pattern = "yyyy-mm-dd'T'HH:mm:ssXXX")
        public void setDate(Date date) {
            this.date = date;
        }
    }
}
