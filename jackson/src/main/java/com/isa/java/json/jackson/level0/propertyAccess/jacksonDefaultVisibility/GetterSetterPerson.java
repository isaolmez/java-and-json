package com.isa.java.json.jackson.level0.propertyAccess.jacksonDefaultVisibility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetterSetterPerson {

    private String name;
    private int age;

    public GetterSetterPerson() {
    }

    public GetterSetterPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
