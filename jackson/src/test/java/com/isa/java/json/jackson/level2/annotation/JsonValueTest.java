package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonValueTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        String result = mapper.writeValueAsString(AnEnum.ONE);
        System.out.println(result);

        assertThat(result, containsString("1"));
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "1";

        AnEnum actual = mapper.readValue(val, AnEnum.class);
        System.out.println(actual);

        assertThat(actual, equalTo(AnEnum.ONE));
    }
}
