package com.isa.java.json.jackson.propertyAccess.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import lombok.Data;
import org.junit.Test;

public class NullPropertyControlSerializeTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize_WithNonNullInclusion_OnClass() throws JsonProcessingException {

        @JsonInclude(Include.NON_NULL)
        @Data
        class Car {

            private String name;
            private int age = 12;
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WithNonNullInclusion_OnMapper() throws JsonProcessingException {

        @Data
        class Car {

            private String name;
            private int age = 12;
        }

        Car car = new Car();

        objectMapper.setSerializationInclusion(Include.NON_NULL);
        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }
}
