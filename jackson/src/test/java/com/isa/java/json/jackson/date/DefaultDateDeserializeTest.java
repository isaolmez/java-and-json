package com.isa.java.json.jackson.date;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Test;

public class DefaultDateDeserializeTest {

    private ObjectMapper mapper = new ObjectMapper();

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+03:00"));
    }

    @Test(expected = JsonParseException.class)
    public void shouldNotDeserializeDateString_WithoutQuotes() throws IOException {
        final String dateString = "2017-06-01";

        mapper.readValue(dateString, Date.class);
    }

    @Test
    public void shouldDeserializeTimestampValue_ForDate() throws IOException {
        String timestamp = "1514109369819";

        java.util.Date deserialized = mapper.readValue(timestamp, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 12:56:09+0300");
    }

    @Test
    public void shouldDeserializeTimestampValue_ForSqlDate() throws IOException {
        String timestamp = "1514109369819";

        java.sql.Date deserialized = mapper.readValue(timestamp, java.sql.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 12:56:09+0300");
        assertThat(deserialized.toString()).isEqualTo("2017-12-24");
    }

    @Test
    public void shouldDeserializeDateString_ForDate() throws IOException {
        String dateString = "\"2017-12-24\"";

        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 03:00:00+0300");
    }

    @Test
    public void shouldDeserializeDateString_ForSqlDate() throws IOException {
        String dateString = "\"2017-12-24\"";

        java.sql.Date deserialized = mapper.readValue(dateString, java.sql.Date.class);

        assertThat(deserialized.toString()).isEqualTo("2017-12-24");
        assertDateIsCorrect(deserialized, "2017-12-24 03:00:00+0300");
    }

    @Test
    public void shouldDeserializeDateString_ForDate_WithMapperTimeZone() throws IOException {
        String dateString = "\"2017-12-24\"";
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+3"));

        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 00:00:00+0300");
    }

    @Test
    public void shouldDeserializeDateString_WithZone() throws IOException {
        String dateString = "\"2017-12-24T13:39:32Z\"";

        System.out.println(mapper.getDeserializationConfig().getTimeZone());
        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 16:39:32+0300");
    }

    @Test
    public void shouldDeserializeDateString_WithZone2() throws IOException {
        String dateString = "\"2017-12-24T13:39:32+00:00\"";

        System.out.println(mapper.getDeserializationConfig().getTimeZone());
        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 16:39:32+0300");
    }

    @Test
    public void shouldDeserializeDateString_WithoutZone() throws IOException {
        String dateString = "\"2017-12-24T13:39:32\"";

        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 16:39:32+0300");
    }

    @Test
    public void shouldDeserializeDateString_WithZone_WithMapperTimeZone() throws IOException {
        String dateString = "\"2017-12-24T13:39:32.000Z\"";
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));

        System.out.println(mapper.getDeserializationConfig().getTimeZone());
        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 16:39:32+0300");
    }

    @Test
    public void shouldDeserializeDateString_WithGmtZone_WithMapperTimeZone() throws IOException {
        String dateString = "\"2017-12-24T13:39:32+00:00\"";
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));
        System.out.println(mapper.getDeserializationConfig().getTimeZone());

        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 16:39:32+0300");
    }

    @Test
    public void shouldDeserializeDateString_WithoutZone_WithMapperTimeZone() throws IOException {
        String dateString = "\"2017-12-24T13:39:32\"";

        mapper.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));
        java.util.Date deserialized = mapper.readValue(dateString, java.util.Date.class);

        assertDateIsCorrect(deserialized, "2017-12-24 13:39:32+0300");
    }

    private void assertDateIsCorrect(Date date, String formattedDate) {
        assertThat(dateFormat.format(date)).isEqualTo(formattedDate);
    }
}
