package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.deserialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PublicFieldDeserializeTest extends BaseJacksonTest {

    @Test
    public void shouldDeserialize() throws IOException {
        final String json = "{\"age\":12}";

        Person deserialized = objectMapper.readValue(json, Person.class);

        assertThat(deserialized.age).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithSetter() throws IOException {
        final String json = "{\"age\":12}";

        PersonWithSetter deserialized = objectMapper.readValue(json, PersonWithSetter.class);

        assertThat(deserialized.age).isEqualTo(999);
    }

    static class Person {

        public int age;
    }

    static class PersonWithSetter {

        public int age;

        public void setAge(int age) {
            this.age = 999;
        }
    }
}
