package com.isa.java.json.jackson.propertyAccess.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyControlDeserializeTest extends BaseJacksonTest {

    @Test
    public void shouldDeserialize_WithMissing() throws IOException {
        final String json = "{\"age\":12}";

        Person deserialized = objectMapper.readValue(json, Person.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithUnknown() throws IOException {
        final String json = "{\"age\":12,\"weight\":99}";

        objectMapper.readValue(json, Person.class);
    }

    @Test
    public void shouldDeserialize_WithUnknown_ViaReader() throws IOException {
        final String json = "{\"age\":12,\"weight\":99}";

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Person deserialized = objectMapper.readValue(json, Person.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithUnknown_ViaClass() throws IOException {
        final String json = "{\"age\":12,\"weight\":99}";

        PersonWithControl deserialized = objectMapper.readValue(json, PersonWithControl.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldNotDeserialize_IgnoredOnProperty() throws IOException {
        final String json = "{\"name\":\"John\",\"age\":12}";

        PersonWithIgnoreOnProperty deserialized = objectMapper.readValue(json, PersonWithIgnoreOnProperty.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldNotDeserialize_IgnoredOnClass() throws IOException {
        final String json = "{\"name\":\"John\",\"age\":12}";

        PersonWithIgnoreOnClass deserialized = objectMapper.readValue(json, PersonWithIgnoreOnClass.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    static class Person {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class PersonWithControl {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    static class PersonWithIgnoreOnProperty {

        @JsonIgnore
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @JsonIgnoreProperties(value = "name")
    static class PersonWithIgnoreOnClass {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
