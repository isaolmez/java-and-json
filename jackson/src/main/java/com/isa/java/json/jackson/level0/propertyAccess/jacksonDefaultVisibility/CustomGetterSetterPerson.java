package com.isa.java.json.jackson.level0.propertyAccess.jacksonDefaultVisibility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomGetterSetterPerson {

    private String name;
    private int age;

    public CustomGetterSetterPerson() {
    }

    public CustomGetterSetterPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = "constant";
    }
}
