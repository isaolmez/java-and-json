package com.isa.java.json.jackson.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyControlAllowSettersTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_WithoutIgnored() throws IOException {
        Person person = new Person();
        person.setName("john");
        person.setAge(12);

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldDeserialize_WithIgnored() throws IOException {
        final String json = "{\"age\":12,\"name\":\"john\"}";

        Person deserialized = objectMapper.readValue(json, Person.class);

        assertThat(deserialized.getName()).isEqualTo("john");
        assertThat(deserialized.getAge()).isEqualTo(12);
    }

    @JsonIgnoreProperties(value = "name", allowSetters = true)
    private static class Person {

        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
