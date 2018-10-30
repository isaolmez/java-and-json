package com.isa.java.json.jackson.propertyNaming;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyNamingTest extends BaseJacksonTest {

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnField() throws JsonProcessingException {
        PersonOnField personOnField = new PersonOnField();
        personOnField.setName("john");
        personOnField.setAge(12);

        String json = objectMapper.writeValueAsString(personOnField);

        assertThat(json).isEqualTo("{\"age\":12,\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnField() throws IOException {
        final String json = "{\"age\":12,\"firstName\":\"john\"}";

        PersonOnField person = objectMapper.readValue(json, PersonOnField.class);

        assertThat(person.getName()).isEqualTo("john");
        assertThat(person.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnGetter() throws JsonProcessingException {
        PersonOnGetter personOnGetter = new PersonOnGetter();
        personOnGetter.setName("john");
        personOnGetter.setAge(12);

        String json = objectMapper.writeValueAsString(personOnGetter);

        assertThat(json).isEqualTo("{\"age\":12,\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnGetter() throws IOException {
        final String json = "{\"age\":12,\"firstName\":\"john\"}";

        PersonOnGetter person = objectMapper.readValue(json, PersonOnGetter.class);

        assertThat(person.getName()).isEqualTo("john");
        assertThat(person.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnSetter() throws JsonProcessingException {
        PersonOnSetter personOnSetter = new PersonOnSetter();
        personOnSetter.setName("john");
        personOnSetter.setAge(12);

        String json = objectMapper.writeValueAsString(personOnSetter);

        assertThat(json).isEqualTo("{\"age\":12,\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnSetter() throws IOException {
        final String json = "{\"age\":12,\"firstName\":\"john\"}";

        PersonOnSetter person = objectMapper.readValue(json, PersonOnSetter.class);

        assertThat(person.getName()).isEqualTo("john");
        assertThat(person.getAge()).isEqualTo(12);
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnCustom() throws JsonProcessingException {
        PersonOnCustom personOnCustom = new PersonOnCustom();
        personOnCustom.setName("john");
        personOnCustom.setAge(12);

        String json = objectMapper.writeValueAsString(personOnCustom);

        assertThat(json).isEqualTo("{\"name\":\"john\",\"age\":12,\"salary\":120}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnCustom() throws IOException {
        final String json = "{\"name\":\"john\",\"age\":12}";

        PersonOnCustom person = objectMapper.readValue(json, PersonOnCustom.class);

        assertThat(person.getName()).isEqualTo("john");
        assertThat(person.getAge()).isEqualTo(12);
    }

    private static class PersonOnField {

        @JsonProperty("firstName")
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

    private static class PersonOnGetter {

        private String name;
        private int age;

        @JsonProperty("firstName")
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

    private static class PersonOnSetter {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        @JsonProperty("firstName")
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

    private static class PersonOnCustom {

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

        @JsonProperty("salary")
        public int calculateSalary() {
            return 120;
        }
    }
}
