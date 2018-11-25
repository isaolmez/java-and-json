package com.isa.java.json.jackson.propertyNaming;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyNamingTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        Person person = new Person();
        person.setName("john");

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"name\":\"john\"}");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final String json = "{\"name\":\"john\"}";

        Person person = objectMapper.readValue(json, Person.class);

        assertThat(person.getName()).isEqualTo("john");
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnField() throws JsonProcessingException {
        PersonOnField personOnField = new PersonOnField();
        personOnField.setName("john");

        String json = objectMapper.writeValueAsString(personOnField);

        assertThat(json).isEqualTo("{\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnField() throws IOException {
        final String json = "{\"firstName\":\"john\"}";

        PersonOnField person = objectMapper.readValue(json, PersonOnField.class);

        assertThat(person.getName()).isEqualTo("john");
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnGetter() throws JsonProcessingException {
        PersonOnGetter personOnGetter = new PersonOnGetter();
        personOnGetter.setName("john");

        String json = objectMapper.writeValueAsString(personOnGetter);

        assertThat(json).isEqualTo("{\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnGetter() throws IOException {
        final String json = "{\"firstName\":\"john\"}";

        PersonOnGetter person = objectMapper.readValue(json, PersonOnGetter.class);

        assertThat(person.getName()).isEqualTo("john");
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnSetter() throws JsonProcessingException {
        PersonOnSetter personOnSetter = new PersonOnSetter();
        personOnSetter.setName("john");

        String json = objectMapper.writeValueAsString(personOnSetter);

        assertThat(json).isEqualTo("{\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnSetter() throws IOException {
        final String json = "{\"firstName\":\"john\"}";

        PersonOnSetter person = objectMapper.readValue(json, PersonOnSetter.class);

        assertThat(person.getName()).isEqualTo("john");
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenJsonPropertyIsOnCustom() throws JsonProcessingException {
        PersonOnCustom personOnCustom = new PersonOnCustom();
        personOnCustom.setName("john");

        String json = objectMapper.writeValueAsString(personOnCustom);

        assertThat(json).isEqualTo("{\"name\":\"john\",\"salary\":120}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenJsonPropertyIsOnCustom() throws IOException {
        final String json = "{\"name\":\"john\"}";

        PersonOnCustom person = objectMapper.readValue(json, PersonOnCustom.class);

        assertThat(person.getName()).isEqualTo("john");
    }

    private static class Person {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PersonOnField {

        @JsonProperty("firstName")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PersonOnGetter {

        private String name;

        @JsonProperty("firstName")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PersonOnSetter {

        private String name;

        public String getName() {
            return name;
        }

        @JsonProperty("firstName")
        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PersonOnCustom {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("salary")
        public int calculateSalary() {
            return 120;
        }
    }
}
