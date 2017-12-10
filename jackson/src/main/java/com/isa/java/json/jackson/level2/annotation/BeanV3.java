package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanV3 {

    @JsonProperty("theName")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
