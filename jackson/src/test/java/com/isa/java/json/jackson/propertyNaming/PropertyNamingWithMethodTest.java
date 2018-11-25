package com.isa.java.json.jackson.propertyNaming;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyNamingWithMethodTest extends BaseJacksonTest {

    @Test
    public void shouldChangeName_ForSerialization_WhenGetterSetterIsRenamed() throws JsonProcessingException {
        PersonWithRenamedGetterSetter personWithRenamedGetterSetter = new PersonWithRenamedGetterSetter();
        personWithRenamedGetterSetter.setFirstName("john");

        String json = objectMapper.writeValueAsString(personWithRenamedGetterSetter);

        assertThat(json).isEqualTo("{\"firstName\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenGetterSetterIsRenamed() throws IOException {
        final String json = "{\"firstName\":\"john\"}";

        PersonWithRenamedGetterSetter person = objectMapper.readValue(json, PersonWithRenamedGetterSetter.class);

        assertThat(person.getFirstName()).isEqualTo("john");
    }

    @Test
    public void shouldChangeName_ForSerialization_WhenGetterIsRenamed() throws JsonProcessingException {
        PersonWithRenamedGetter personWithRenamedGetter = new PersonWithRenamedGetter();
        personWithRenamedGetter.setName("john");

        String json = objectMapper.writeValueAsString(personWithRenamedGetter);

        assertThat(json).isEqualTo("{\"firstName\":\"john\"}");
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotReadChangedName_ForDeserialization_WhenGetterIsRenamed() throws IOException {
        final String json = "{\"firstName\":\"john\"}";

        objectMapper.readValue(json, PersonWithRenamedGetter.class);
    }

    @Test
    public void shouldNotChangeName_ForSerialization_WhenSetterIsRenamed() throws JsonProcessingException {
        PersonWithRenamedSetter personWithRenamedSetter = new PersonWithRenamedSetter();
        personWithRenamedSetter.setFirstName("john");

        String json = objectMapper.writeValueAsString(personWithRenamedSetter);

        assertThat(json).isEqualTo("{\"name\":\"john\"}");
    }

    @Test
    public void shouldReadChangedName_ForDeserialization_WhenSetterIsRenamed() throws IOException {
        final String json = "{\"firstName\":\"john\"}";

        PersonWithRenamedSetter person = objectMapper.readValue(json, PersonWithRenamedSetter.class);

        assertThat(person.getName()).isEqualTo("john");
    }

    private static class PersonWithRenamedGetterSetter {

        private String name;

        public String getFirstName() {
            return name;
        }

        public void setFirstName(String name) {
            this.name = name;
        }
    }

    private static class PersonWithRenamedGetter {

        private String name;

        public String getFirstName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PersonWithRenamedSetter {

        private String name;

        public String getName() {
            return name;
        }

        public void setFirstName(String name) {
            this.name = name;
        }
    }
}
