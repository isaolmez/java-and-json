package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.serialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class PublicGetterSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_PublicGetters() throws JsonProcessingException {

        class Person {

            private int age = 12;

            public int getAge() {
                return age;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicGetters_AdditionalMethod() throws JsonProcessingException {

        class Person {

            private int age = 12;

            public int getAge() {
                return age;
            }

            public int getAdditional() {
                return age;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12,\"additional\":12}");
    }
}
