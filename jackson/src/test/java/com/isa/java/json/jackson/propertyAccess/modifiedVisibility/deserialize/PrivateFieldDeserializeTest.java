package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.deserialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PrivateFieldDeserializeTest extends BaseJacksonTest {

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithPrivateFields() throws IOException {
        final String json = "{\"age\":12}";

        objectMapper.readValue(json, Person.class);
    }

    @Test
    public void shouldDeserialize_WithPrivateFields() throws IOException {
        final String json = "{\"age\":12}";

        PersonWithAnnotation deserialized = objectMapper.readValue(json, PersonWithAnnotation.class);

        assertThat(deserialized.age).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithPrivateFields_ViaReader() throws IOException {
        final String json = "{\"age\":12}";

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        Person deserialized = objectMapper.readValue(json, Person.class);

        assertThat(deserialized.age).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithPrivateFields_WithSetter() throws IOException {
        final String json = "{\"age\":12}";

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        PersonWithSetter deserialized = objectMapper.readValue(json, PersonWithSetter.class);

        assertThat(deserialized.age).isEqualTo(999);
    }

    static class Person {

        private int age;
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    static class PersonWithAnnotation {

        private int age;
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    static class PersonWithSetter {

        private int age;

        public void setAge(int age) {
            this.age = 999;
        }
    }
}
