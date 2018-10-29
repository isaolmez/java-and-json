package com.isa.java.json.jackson.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyControlSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_WithIgnoreOnField() throws JsonProcessingException {

        class Person {

            @JsonIgnore
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

        assertThat(json).isEqualTo("{\"additional\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnProperty() throws JsonProcessingException {

        class Person {

            private int age = 12;

            @JsonIgnore
            public int getAge() {
                return age;
            }

            public int getAdditional() {
                return age;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"additional\":12}");
    }
    
    @Test
    public void shouldSerialize_WithIgnoreOnAdditionalProperty() throws JsonProcessingException {

        class Person {

            private int age = 12;

            public int getAge() {
                return age;
            }

            @JsonIgnore
            public int getAdditional() {
                return age;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnClass() throws JsonProcessingException {

        @JsonIgnoreProperties("name")
        class Person {

            private int age = 12;
            private String name = "john";

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnClass_AndAllowingGetters() throws IOException {

        @JsonIgnoreProperties(value = "name", allowGetters = true)
        class Person {

            private int age = 12;
            private String name = "john";

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        Person person = new Person();

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"age\":12,\"name\":\"john\"}");
    }
}
