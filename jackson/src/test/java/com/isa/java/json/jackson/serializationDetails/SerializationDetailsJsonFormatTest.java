package com.isa.java.json.jackson.serializationDetails;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class SerializationDetailsJsonFormatTest extends BaseJacksonTest {

    private Date birthDate;

    @Before
    public void setUp() {
        Instant instant = LocalDateTime.of(2008, 2, 3, 12, 45)
                                       .toInstant(ZoneOffset.UTC);
        birthDate = Date.from(instant);
    }

    @Test
    public void shouldSerializeDate_AsTimestamp_ByDefault() throws JsonProcessingException {
        class Person {

            private Date birthDate;

            public Date getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(Date birthDate) {
                this.birthDate = birthDate;
            }
        }

        final Person person = new Person();
        person.setBirthDate(birthDate);

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"birthDate\":1202042700000}");
    }

    @Test
    public void shouldFormatDate_WhenJsonFormatIsGiven_WithPattern() throws JsonProcessingException {
        class Person {

            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date birthDate;

            public Date getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(Date birthDate) {
                this.birthDate = birthDate;
            }
        }

        final Person person = new Person();
        person.setBirthDate(birthDate);

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"birthDate\":\"2008-02-03\"}");
    }

    @Test
    public void shouldFormatDate_WhenJsonFormatIsGiven_WithShape() throws JsonProcessingException {
        class Person {

            @JsonFormat(shape = Shape.STRING)
            private Date birthDate;

            public Date getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(Date birthDate) {
                this.birthDate = birthDate;
            }
        }

        final Person person = new Person();
        person.setBirthDate(birthDate);

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"birthDate\":\"2008-02-03T12:45:00.000+0000\"}");
    }

    @Test
    public void shouldSerializeEnum_AsConstantString_ByDefault() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(Job.TEACHER);

        assertThat(json).isEqualTo("\"TEACHER\"");
    }

    @Test
    public void shouldSerializeEnum_AsObject_WhenJsonFormatIsGiven_WithShape() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(JobWithShape.TEACHER);

        assertThat(json).isEqualTo("{\"value\":\"teacher\"}");
    }

    @Test
    public void shouldSerializeEnum_AsValue_WhenJsonValueIsGiven() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(JobWithValue.TEACHER);

        assertThat(json).isEqualTo("\"teacher\"");
    }

    @Test
    public void shouldSerializeInteger_AsNumber_ByDefault() throws JsonProcessingException {
        class Race {

            private int length;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }
        }

        final Race race = new Race();
        race.setLength(99);

        String json = objectMapper.writeValueAsString(race);

        assertThat(json).isEqualTo("{\"length\":99}");
    }

    @Test
    public void shouldSerializeInteger_AsString_WhenJsonFormatIsGiven_WithShape() throws JsonProcessingException {
        class Race {

            @JsonFormat(shape = Shape.STRING)
            private int length;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }
        }

        final Race race = new Race();
        race.setLength(99);

        String json = objectMapper.writeValueAsString(race);

        assertThat(json).isEqualTo("{\"length\":\"99\"}");
    }

    public enum Job {
        TEACHER("teacher");

        private final String value;

        Job(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @JsonFormat(shape = Shape.OBJECT)
    public enum JobWithShape {
        TEACHER("teacher");

        private final String value;

        JobWithShape(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum JobWithValue {
        TEACHER("teacher");

        @JsonValue
        private final String value;

        JobWithValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
