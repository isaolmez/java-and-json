package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

public class BeanV1 {

    private String name;

    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }

    @JsonGetter("theName")
    public String getName() {
        return name;
    }

    @JsonSetter("theName")
    public void setName(String name) {
        this.name = name;
    }
}
