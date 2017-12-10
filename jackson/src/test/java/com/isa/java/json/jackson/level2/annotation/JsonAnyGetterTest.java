package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class JsonAnyGetterTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        BeanV1 bean = new BeanV1();
        bean.setName("v1");
        bean.getProperties().put("attr1", "val1");
        bean.getProperties().put("attr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);
        assertThat(result, containsString("theName"));
        assertThat(result, containsString("v1"));
        assertThat(result, containsString("attr1"));
        assertThat(result, containsString("val1"));
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "{\"theName\":\"v1\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";
        ObjectMapper mapper = new ObjectMapper();
        BeanV1 actual = mapper.readValue(val, BeanV1.class);

        System.out.println(actual);
        assertThat(actual.getName(), equalTo("v1"));
    }
}
