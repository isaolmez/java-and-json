package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.serialize;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicFieldSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_PublicFields() throws JsonProcessingException {

        class Person {

            public int age = 12;
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicFields2() throws JsonProcessingException {

        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        class Person {

            public int age = 12;
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicFields_ViaWriter() throws JsonProcessingException {

        class Person {

            public int age = 12;
        }

        Person person = new Person();

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicFields_WithPrioritizingGetter() throws JsonProcessingException {

        class Person {

            public int age = 12;

            public int getAge() {
                return 999;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":999}");
    }
}
