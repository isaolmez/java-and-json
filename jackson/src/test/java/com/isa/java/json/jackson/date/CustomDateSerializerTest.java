package com.isa.java.json.jackson.date;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.util.Date;
import org.junit.Test;

public class CustomDateSerializerTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        Person person = new Person();
        person.setBirthDate(new Date(0));

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"birthDate\":\"1970-01-01 02:00:00\"}");
    }

    static class Person {

        @JsonSerialize(using = CustomDateSerializer.class)
        private Date birthDate;

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }
    }
}