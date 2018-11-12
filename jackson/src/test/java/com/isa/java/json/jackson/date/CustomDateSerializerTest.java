package com.isa.java.json.jackson.date;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class CustomDateSerializerTest extends BaseJacksonTest {

    private Date birthDate;

    @Before
    public void setUp() {
        Instant instant = LocalDateTime.of(2008, 2, 3, 12, 45)
                                       .toInstant(ZoneOffset.UTC);
        birthDate = Date.from(instant);
    }

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        Person person = new Person();
        person.setBirthDate(birthDate);

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"birthDate\":\"2008/02/03 12:45:00+0000\"}");
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