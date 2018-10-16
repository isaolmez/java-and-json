package com.isa.java.json.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class JsonAnyGetterTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        BeanV1 bean = new BeanV1();
        bean.setName("v1");
        bean.getProperties().put("attr1", "val1");
        bean.getProperties().put("attr2", "val2");

        String json = objectMapper.writeValueAsString(bean);

        assertThat(json).isEqualTo("{\"name\":\"v1\",\"attr2\":\"val2\",\"attr1\":\"val1\"}");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "{\"name\":\"v1\",\"attr1\":\"val1\",\"attr2\":\"val2\"}";

        BeanV1 deserialized = objectMapper.readValue(val, BeanV1.class);

        assertThat(deserialized.getName()).isEqualTo("v1");
        assertThat(deserialized.getProperties().get("attr1")).isEqualTo("val1");
        assertThat(deserialized.getProperties().get("attr2")).isEqualTo("val2");
    }

    static class BeanV1 {

        private String name;

        private Map<String, String> properties = new HashMap<>();

        @JsonAnyGetter
        public Map<String, String> getProperties() {
            return properties;
        }

        @JsonAnySetter
        public void add(String key, String value) {
            properties.put(key, value);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
