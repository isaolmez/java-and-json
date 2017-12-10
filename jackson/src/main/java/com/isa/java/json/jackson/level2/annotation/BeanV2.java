package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class BeanV2 {

    private String name;

    @JsonGetter("theName")
    public String getName() {
        return name;
    }

    @JsonSetter("theName")
    public void setName(String name) {
        this.name = name;
    }
}
