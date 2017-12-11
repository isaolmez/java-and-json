package com.isa.java.json.jackson.level1.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class ItemDeserializer extends StdDeserializer<Item> {

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> t) {
        super(t);
    }

    @Override
    public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int id = (Integer) node.get("id").numberValue();
        String itemName = node.get("itemName").asText();
        int userId = (Integer) node.get("createdBy").numberValue();

        return new Item(id, itemName, new User(userId, null));
    }
}
