package com.isa.java.json.jackson.level0.propertyAccess.jacksonDefaultVisibility;

import lombok.Getter;

@Getter
public class GetterPerson {

    private String name;
    private int age;

    public GetterPerson() {
    }

    public GetterPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
