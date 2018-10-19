package com.isa.java.json.jackson.propertyAccess.defaultVisibility;

public class GetterPerson {

    private String name;
    private int age;

    public GetterPerson() {
    }

    public GetterPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
