package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonPropertyTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        BeanV3 bean = new BeanV3();
        bean.setName("v3");

        String result = mapper.writeValueAsString(bean);
        System.out.println(result);

        assertThat(result, containsString("theName"));
        assertThat(result, containsString("v3"));
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "{\"theName\":\"v3\"}";

        BeanV3 actual = mapper.readValue(val, BeanV3.class);
        System.out.println(actual.getName());

        assertThat(actual.getName(), equalTo("v3"));
    }
}
