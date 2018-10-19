package com.isa.java.json.jackson.propertyAccess.defaultVisibility;

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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
