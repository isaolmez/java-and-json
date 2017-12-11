package com.isa.java.json.jackson.level1.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class JsonGetterTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        BeanV2 bean = new BeanV2();
        bean.setName("v2");

        String result = objectMapper.writeValueAsString(bean);

        assertThat(result).isEqualTo("{\"theName\":\"v2\"}");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "{\"theName\":\"v2\"}";

        BeanV2 actual = objectMapper.readValue(val, BeanV2.class);

        assertThat(actual.getName()).isEqualTo("v2");
    }


    static class BeanV2 {

        private String name;

        @JsonGetter("theName")
        public String getName() {
            return name;
        }

        @JsonSetter("theName")
        public void setName(String name) {
            this.name = name;
        }
    }
}
