package com.isa.java.json.jackson.date;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.junit.Test;

public class DefaultDateSerializeTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSerializeUtilDateAsTimestamp() throws JsonProcessingException {
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        String serialized = mapper.writeValueAsString(date);

        assertThat(serialized).isEqualTo(String.valueOf(now));
    }

    @Test
    public void shouldSerializeSqlDateAsTimestamp() throws JsonProcessingException {
        long now = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(now);

        String serialized = mapper.writeValueAsString(date);

        assertThat(serialized).isEqualTo(String.valueOf(now));
    }
}
