package com.isa.java.json.jackson.level0.propertyAccess.jacksonModifiedVisibility.serialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class PrivateGetterSerializeTest extends BaseJacksonTest {

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_PrivateGetters() throws JsonProcessingException {

        class Car {

            private int age = 12;

            private int getAge() {
                return age;
            }
        }

        Car car = new Car();

        objectMapper.writeValueAsString(car);
    }

    @Test
    public void shouldSerialize_PrivateGetters() throws JsonProcessingException {

        @JsonAutoDetect(getterVisibility = Visibility.ANY)
        class Car {

            private int age = 12;

            private int getAge() {
                return age;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PrivateGetters_ViaWriter() throws JsonProcessingException {

        class Car {

            private int age = 12;

            private int getAge() {
                return age;
            }
        }

        Car car = new Car();

        objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.ANY);
        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }
}
