package com.isa.java.json.jackson.propertyAccess.defaultVisibility;

import lombok.ToString;

@ToString
public class CustomGetterPerson {

    private String name;
    private int age;

    public CustomGetterPerson() {
    }

    public CustomGetterPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return 999;
    }

    public String getName() {
        return name;
    }

    public int getPreviousAge() {
        return age - 1;
    }

    public int nextAge() {
        return age + 1;
    }
}
