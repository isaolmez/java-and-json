package com.isa.java.json.jackson.propertyAccess.defaultVisibility;

public class PrivatePerson {

    private String name;

    private int age;

    public PrivatePerson() {
    }

    public PrivatePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
