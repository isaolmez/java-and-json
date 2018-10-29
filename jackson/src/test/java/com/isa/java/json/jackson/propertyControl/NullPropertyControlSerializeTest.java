package com.isa.java.json.jackson.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class NullPropertyControlSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_WithNonNullInclusion_OnClass() throws JsonProcessingException {

        @JsonInclude(Include.NON_NULL)
        class Person {

            private String name;
            private int age = 12;

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WithNonNullInclusion_OnMapper() throws JsonProcessingException {

        class Person {

            private String name;
            private int age = 12;

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }
        }

        Person person = new Person();

        objectMapper.setSerializationInclusion(Include.NON_NULL);
        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }
}
