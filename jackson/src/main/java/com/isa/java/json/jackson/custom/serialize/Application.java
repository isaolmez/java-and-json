package com.isa.java.json.jackson.custom.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Application {

    public static void main(String[] args) {
        serialize();
        serializeWithCustomSerializer();
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

    public static void serializeWithCustomSerializer() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Item.class, new ItemSerializer());
            mapper.registerModule(simpleModule);

            Item item = new Item(1, "item", new User(99, "john"));
            System.out.println(mapper.writeValueAsString(item));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
