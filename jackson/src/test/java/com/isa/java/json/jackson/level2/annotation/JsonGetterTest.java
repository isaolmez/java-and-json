package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class JsonGetterTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        BeanV2 bean = new BeanV2();
        bean.setName("v2");

        String result = mapper.writeValueAsString(bean);
        System.out.println(result);
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "{\"theName\":\"v2\"}";

        BeanV2 actual = mapper.readValue(val, BeanV2.class);
        System.out.println(actual.getName());

        assertThat(actual.getName(), equalTo("v2"));
    }
}
