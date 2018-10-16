package com.isa.java.json.jackson.propertyAccess.modifiedVisibility.serialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class PackagePrivateFieldSerializeTest extends BaseJacksonTest {

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_PackagePrivateFields_Default() throws JsonProcessingException {

        class Car {

            int age = 12;
        }

        Car car = new Car();

        objectMapper.writeValueAsString(car);
    }

    @Test
    public void shouldSerialize_PackagePrivateFields() throws JsonProcessingException {

        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        class Car {

            int age = 12;
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test
    public void shouldSerialize_WhilePrioritizingGetter() throws JsonProcessingException {

        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        class Car {

            int age = 12;

            public int getAge() {
                return age + 1;
            }
        }

        Car car = new Car();

        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":13}");
    }

    @Test
    public void shouldSerialize_PackagePrivateFields_ViaWriter() throws JsonProcessingException {

        class Car {

            int age = 12;
        }

        Car car = new Car();

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"age\":12}");
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_PackagePrivateFields() throws JsonProcessingException {

        class Car {

            int age = 12;
        }

        Car car = new Car();

        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.PROTECTED_AND_PUBLIC);
        objectMapper.writeValueAsString(car);
    }
}
