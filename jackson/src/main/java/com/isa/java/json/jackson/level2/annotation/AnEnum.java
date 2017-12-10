package com.isa.java.json.jackson.level2.annotation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonRootName(value = "count")
public enum AnEnum {
    ZERO(0),
    ONE(1),
    TWO(2);

    private final int value;

    AnEnum(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
