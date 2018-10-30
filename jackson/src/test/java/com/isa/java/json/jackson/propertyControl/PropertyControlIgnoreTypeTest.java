package com.isa.java.json.jackson.propertyControl;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyControlIgnoreTypeTest extends BaseJacksonTest {

    @Test
    public void shouldNotDeserializeAddressFields() throws IOException {
        final String json = "{\"name\":\"john\", \"address\":{\"street\":\"test street\", \"houseNumber\":11}}";

        Person person = objectMapper.readValue(json, Person.class);

        assertThat(person.getName()).isEqualTo("john");
        assertThat(person.getAddress()).isNull();
    }

    @Test
    public void shouldNotSerializeAddressFields() throws JsonProcessingException {
        final Person person = new Person();
        person.setName("john");
        final Address address = new Address();
        address.setStreet("test street");
        address.setHouseNumber(11);
        person.setAddress(address);

        String json = objectMapper.writeValueAsString(person);

        assertThat(json).isEqualTo("{\"name\":\"john\"}");
    }

    private static class Person {

        private String name;
        private Address address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    @JsonIgnoreType
    private static class Address {

        private String street;
        private int houseNumber;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }
    }
}
