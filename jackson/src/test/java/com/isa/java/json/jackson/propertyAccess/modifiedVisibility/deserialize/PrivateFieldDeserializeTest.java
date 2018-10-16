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

        objectMapper.readValue(json, Car.class);
    }

    @Test
    public void shouldDeserialize_WithPrivateFields() throws IOException {
        final String json = "{\"age\":12}";

        CarWithAnnotation deserialized = objectMapper.readValue(json, CarWithAnnotation.class);

        assertThat(deserialized.age).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithPrivateFields_ViaReader() throws IOException {
        final String json = "{\"age\":12}";

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        Car deserialized = objectMapper.readValue(json, Car.class);

        assertThat(deserialized.age).isEqualTo(12);
    }

    @Test
    public void shouldDeserialize_WithPrivateFields_WithSetter() throws IOException {
        final String json = "{\"age\":12}";

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        CarWithSetter deserialized = objectMapper.readValue(json, CarWithSetter.class);

        assertThat(deserialized.age).isEqualTo(999);
    }

    static class Car {

        private int age;
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    static class CarWithAnnotation {

        private int age;
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    static class CarWithSetter {

        private int age;

        public void setAge(int age) {
            this.age = 999;
        }
    }
}
