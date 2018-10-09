package com.isa.java.json.jackson.level0.propertyAccess.jacksonDefaultVisibility;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isa.java.json.jackson.BaseJacksonTest;
import org.junit.Test;

public class PropertyAccessSerializeTest extends BaseJacksonTest {

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_WithPrivateFields() throws JsonProcessingException {
        PrivatePerson privatePerson = new PrivatePerson("john", 21);

        objectMapper.writeValueAsString(privatePerson);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldNotSerialize_WithDefaultFields() throws JsonProcessingException {
        DefaultPerson defaultPerson = new DefaultPerson("john", 21);

        objectMapper.writeValueAsString(defaultPerson);
    }

    @Test
    public void shouldSerialize_WithPublicFields() throws JsonProcessingException {
        PublicPerson publicPerson = new PublicPerson("john", 21);

        String json = objectMapper.writeValueAsString(publicPerson);

        assertThat(json).isEqualTo("{\"name\":\"john\",\"age\":21}");
    }

    @Test
    public void shouldSerialize_WithPublicFields_AndNullValues() throws JsonProcessingException {
        PublicPerson publicPerson = new PublicPerson(null, 21);

        String json = objectMapper.writeValueAsString(publicPerson);

        assertThat(json).isEqualTo("{\"name\":null,\"age\":21}");
    }

    @Test
    public void shouldSerialize_WithGetters() throws JsonProcessingException {
        GetterPerson getterPerson = new GetterPerson("john", 21);

        String json = objectMapper.writeValueAsString(getterPerson);

        assertThat(json).isEqualTo("{\"name\":\"john\",\"age\":21}");
    }

    @Test
    public void shouldSerialize_WithGetters_AndNullValues() throws JsonProcessingException {
        GetterPerson getterPerson = new GetterPerson(null, 21);

        String json = objectMapper.writeValueAsString(getterPerson);

        assertThat(json).isEqualTo("{\"name\":null,\"age\":21}");
    }

    @Test
    public void shouldSerialize_WithGetterSetters() throws JsonProcessingException {
        GetterSetterPerson getterSetterPerson = new GetterSetterPerson("john", 21);

        String json = objectMapper.writeValueAsString(getterSetterPerson);

        assertThat(json).isEqualTo("{\"name\":\"john\",\"age\":21}");
    }

    @Test
    public void shouldSerialize_WithCustomGetter_AndAdditionalMethods() throws JsonProcessingException {
        CustomGetterPerson customGetterPerson = new CustomGetterPerson("john", 21);

        String json = objectMapper.writeValueAsString(customGetterPerson);

        assertThat(json).isEqualTo("{\"name\":\"john\",\"age\":999,\"previousAge\":20}");
    }
}