package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.serialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class PrivateFieldSerializeTest extends BaseJacksonTest {

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_PrivateFields_Default() throws JsonProcessingException {

        class Person {

            private int age = 12;
        }

        Person person = new Person();

        objectMapper.writeValueAsString(person);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_PrivateFields() throws JsonProcessingException {

        class Person {

            private int age = 12;
        }

        Person person = new Person();

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.NON_PRIVATE);
        objectMapper.writeValueAsString(person);
    }

    @Test
    public void shouldSerialize_PrivateFields() throws JsonProcessingException {

        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        class Person {

            private int age = 12;
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PrivateFields_ViaWriter() throws JsonProcessingException {

        class Person {

            private int age = 12;
        }

        Person person = new Person();

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PrivateFields_AndGetter() throws JsonProcessingException {

        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        class Person {

            private int age = 12;

            public int getAge() {
                return 999;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":999}");
    }
}
