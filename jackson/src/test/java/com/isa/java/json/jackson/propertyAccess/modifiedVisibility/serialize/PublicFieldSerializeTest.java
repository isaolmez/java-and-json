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
    public void shouldNotSerialize_PublicFields() throws JsonProcessingException {

        class Car {

            public int age = 12;
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicFields() throws JsonProcessingException {

        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        class Car {

            public int age = 12;
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicFields_ViaWriter() throws JsonProcessingException {

        class Car {

            public int age = 12;
        }

        Car car = new Car();

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_PublicFields_WithPrioritizingGetter() throws JsonProcessingException {

        class Car {

            public int age = 12;

            public int getAge() {
                return 999;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":999}");
    }
}
