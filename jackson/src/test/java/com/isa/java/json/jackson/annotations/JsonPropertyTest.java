package com.isa.java.json.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class JsonPropertyTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        MyBean bean = new MyBean();
        bean.setName("john");

        String json = objectMapper.writeValueAsString(bean);

        assertThat(json).isEqualTo("{\"theName\":\"john\"}");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "{\"theName\":\"john\"}";

        MyBean deserialized = objectMapper.readValue(val, MyBean.class);

        assertThat(deserialized.getName()).isEqualTo("john");
    }

    static class MyBean {

        @JsonProperty("theName")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
