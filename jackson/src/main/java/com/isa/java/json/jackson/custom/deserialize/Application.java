package com.isa.java.json.jackson.custom.deserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        serialize();
        deserialize();
        deserializeWithCustomDeserializer();
    }

    public static void serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Item item = new Item(1, "item", new User(99, "john"));
            System.out.println(mapper.writeValueAsString(item));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String input = "{\"id\":1,\"itemName\":\"item\",\"createdBy\": 99}";
            Item item = mapper.readValue(input, Item.class);
            System.out.println(item.getOwner().getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeWithCustomDeserializer() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Item.class, new ItemDeserializer());
            mapper.registerModule(module);

            String input = "{\"id\":1,\"itemName\":\"item\",\"createdBy\": 99}";
            Item item = mapper.readValue(input, Item.class);
            System.out.println(item.getOwner().getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
