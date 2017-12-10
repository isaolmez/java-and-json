package com.isa.java.json.jackson.level0.propertyAccess.jacksonModifiedVisibility.serialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class PublicGetterSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_PublicGetters() throws JsonProcessingException {

        class Car {

            private int age = 12;

            public int getAge() {
                return age;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicGetters_AdditionalMethod() throws JsonProcessingException {

        class Car {

            private int age = 12;

            public int getAge() {
                return age;
            }

            public int getAdditional() {
                return age;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12,\"additional\":12}");
    }
}
