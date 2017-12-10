package com.isa.java.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

public abstract class BaseJacksonTest {

    protected ObjectMapper objectMapper;

    @Before
    public void setUpBase() {
        objectMapper = new ObjectMapper();
    }
}
