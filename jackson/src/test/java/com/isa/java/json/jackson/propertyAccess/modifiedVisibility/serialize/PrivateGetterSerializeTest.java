package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.serialize;

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

        class Person {

            private int age = 12;

            private int getAge() {
                return age;
            }
        }

        Person person = new Person();

        objectMapper.writeValueAsString(person);
    }

    @Test
    public void shouldSerialize_PrivateGetters() throws JsonProcessingException {

        @JsonAutoDetect(getterVisibility = Visibility.ANY)
        class Person {

            private int age = 12;

            private int getAge() {
                return age;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PrivateGetters_ViaWriter() throws JsonProcessingException {

        class Person {

            private int age = 12;

            private int getAge() {
                return age;
            }
        }

        Person person = new Person();

        objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.ANY);
        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }
}
