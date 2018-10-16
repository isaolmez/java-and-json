package com.isa.java.json.jackson.propertyAccess.defaultVisibility;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class PropertyAccessDeserializeTest extends BaseJacksonTest {

    private final String json = "{\"name\":\"john\",\"age\":21}";

    // Private fields

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithPrivateFields() throws IOException {
        objectMapper.readValue(json, PrivatePerson.class);
    }

    // Default fields

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithDefaultFields() throws IOException {
        objectMapper.readValue(json, DefaultPerson.class);
    }

    // Public fields

    @Test
    public void shouldDeserialize_WithPublicFields() throws IOException {
        PublicPerson deserialized = objectMapper.readValue(json, PublicPerson.class);

        assertThat(deserialized.name).isEqualTo("john");
        assertThat(deserialized.age).isEqualTo(21);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithPublicFields_AndUnknownProperty() throws IOException {
        final String someJson = "{\"name\":\"john\",\"age\":21,\"weight\":60}";

        objectMapper.readValue(someJson, PublicPerson.class);
    }

    @Test
    public void shouldDeserialize_WithPublicFields_LessMapping() throws IOException {
        final String someJson = "{\"age\":21}";

        PublicPerson deserialized = objectMapper.readValue(someJson, PublicPerson.class);

        assertThat(deserialized.name).isNull();
        assertThat(deserialized.age).isEqualTo(21);
    }

    // Getter

    @Test
    public void shouldDeserialize_WithGetters() throws IOException {
        GetterPerson deserialized = objectMapper.readValue(json, GetterPerson.class);

        assertThat(deserialized.getName()).isEqualTo("john");
        assertThat(deserialized.getAge()).isEqualTo(21);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithGetters_AndUnknownProperty() throws IOException {
        final String someJson = "{\"name\":\"john\",\"age\":21,\"weight\":60}";

        objectMapper.readValue(someJson, GetterPerson.class);
    }

    @Test
    public void shouldDeserialize_WithGetters_LessMapping() throws IOException {
        final String someJson = "{\"age\":21}";

        GetterPerson deserialized = objectMapper.readValue(someJson, GetterPerson.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(21);
    }

    //Getter setter

    @Test
    public void shouldDeserialize_WithGetterSetters() throws IOException {
        GetterSetterPerson deserialized = objectMapper.readValue(json, GetterSetterPerson.class);

        assertThat(deserialized.getName()).isEqualTo("john");
        assertThat(deserialized.getAge()).isEqualTo(21);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotDeserialize_WithGetterSetters_AndUnknownProperty() throws IOException {
        final String someJson = "{\"name\":\"john\",\"age\":21,\"weight\":60}";

        objectMapper.readValue(someJson, GetterSetterPerson.class);
    }

    @Test
    public void shouldDeserialize_WithGetterSetters_LessMapping() throws IOException {
        final String someJson = "{\"age\":21}";

        GetterSetterPerson deserialized = objectMapper.readValue(someJson, GetterSetterPerson.class);

        assertThat(deserialized.getName()).isNull();
        assertThat(deserialized.getAge()).isEqualTo(21);
    }

    // Custom setter

    @Test
    public void shouldDeserialize_WithCustomSetter() throws IOException {
        CustomGetterSetterPerson deserialized = objectMapper.readValue(json, CustomGetterSetterPerson.class);

        assertThat(deserialized.getName()).isEqualTo("constant");
        assertThat(deserialized.getAge()).isEqualTo(21);
    }
}