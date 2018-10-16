package com.isa.java.json.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.isa.java.json.jackson.BaseJacksonTest;
import java.io.IOException;
import org.junit.Test;

public class JsonValueTest extends BaseJacksonTest {

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(AnEnum.ONE);

        assertThat(result).isEqualTo("1");
    }

    @Test
    public void shouldWrapAndSerialize() throws JsonProcessingException {
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        String result = objectMapper.writeValueAsString(AnEnum.ONE);

        assertThat(result).isEqualTo("{\"count\":1}");
    }

    /**
     * NOTE: when use for Java <code>enum</code>s, one additional feature is
     * that value returned by annotated method is also considered to be the
     * value to deserialize from, not just JSON String to serialize as.
     * This is possible since set of Enum values is constant and it is possible
     * to define mapping, but can not be done in general for POJO types; as such,
     * this is not used for POJO deserialization.
     */

    @Test
    public void shouldDeserialize() throws IOException {
        final String val = "1";

        AnEnum actual = objectMapper.readValue(val, AnEnum.class);

        assertThat(actual).isEqualTo(AnEnum.ONE);
    }

    @Test
    public void shouldUnwrapAndDeserialize() throws IOException {
        final String val = "{\"count\":1}";

        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        AnEnum actual = objectMapper.readValue(val, AnEnum.class);

        assertThat(actual).isEqualTo(AnEnum.ONE);
    }

    @JsonRootName(value = "count")
    public enum AnEnum {
        ZERO(0),
        ONE(1),
        TWO(2);

        private final int value;

        AnEnum(int value) {
            this.value = value;
        }

        @JsonValue
        public int getValue() {
            return value;
        }
    }
}
