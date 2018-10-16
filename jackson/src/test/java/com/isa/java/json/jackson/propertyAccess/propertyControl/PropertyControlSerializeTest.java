package com.isa.java.json.jackson.propertyAccess.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import lombok.Data;
import org.junit.Test;

public class PropertyControlSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_WithIgnoreOnProperty() throws JsonProcessingException {

        class Car {

            private int age = 12;

            public int getAge() {
                return age;
            }

            @JsonIgnore
            public int getAdditional() {
                return age;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnProperty2() throws JsonProcessingException {

        class Car {

            @JsonIgnore
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

        assertThat(json).isEqualTo("{\"additional\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnProperty3() throws JsonProcessingException {

        class Car {

            private int age = 12;

            @JsonIgnore
            public int getAge() {
                return age;
            }

            public int getAdditional() {
                return age;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"additional\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnClass() throws JsonProcessingException {

        @JsonIgnoreProperties("name")
        @Data
        class Car {

            private int age = 12;
            private String name = "john";
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WithIgnoreOnClass2() throws IOException {

        @JsonIgnoreProperties(value = "name", allowGetters = true)
        @Data
        class Car {

            private int age = 12;
            private String name = "john";
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12,\"name\":\"john\"}");
    }
}
