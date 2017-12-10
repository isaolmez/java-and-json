package com.isa.java.json.jackson.level0.propertyAccess.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import lombok.Data;
import org.junit.Test;

public class PropertyControlDeserializeTest extends BaseJacksonTest {

    @Test
    public void shouldDeserialize_WithMissing() throws IOException {
        final String json = "{\"age\":12}";

        Fish deserialized = objectMapper.readValue(json, Fish.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithUnknown() throws IOException {
        final String json = "{\"age\":12,\"weight\":99}";

        objectMapper.readValue(json, Fish.class);
    }

    @Test
    public void shouldDeserialize_WithUnknown_ViaReader() throws IOException {
        final String json = "{\"age\":12,\"weight\":99}";

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Fish deserialized = objectMapper.readValue(json, Fish.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithUnknown_ViaClass() throws IOException {
        final String json = "{\"age\":12,\"weight\":99}";

        FishWithControl deserialized = objectMapper.readValue(json, FishWithControl.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldNotDeserialize_IgnoredOnProperty() throws IOException {
        final String json = "{\"name\":\"nemo\",\"age\":12}";

        FishWithIgnoreOnProperty deserialized = objectMapper.readValue(json, FishWithIgnoreOnProperty.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldNotDeserialize_IgnoredOnClass() throws IOException {
        final String json = "{\"name\":\"nemo\",\"age\":12}";

        FishWithIgnoreOnClass deserialized = objectMapper.readValue(json, FishWithIgnoreOnClass.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Data
    static class Fish {

        private String name;
        private int age;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class FishWithControl {

        private String name;
        private int age;
    }

    @Data
    static class FishWithIgnoreOnProperty {

        @JsonIgnore
        private String name;
        private int age;
    }

    @Data
    @JsonIgnoreProperties(value = "name")
    static class FishWithIgnoreOnClass {

        private String name;
        private int age;
    }
}
