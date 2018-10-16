package com.isa.java.json.jackson.propertyAccess.defaultVisibility;

import lombok.Getter;
import lombok.ToString;

@Getter
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

    public int getPreviousAge() {
        return age - 1;
    }
}
