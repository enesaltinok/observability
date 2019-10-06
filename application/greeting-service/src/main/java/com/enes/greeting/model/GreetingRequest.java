package com.enes.greeting.model;

import javax.validation.constraints.NotBlank;

public class GreetingRequest {

    @NotBlank
    private String name;

    public GreetingRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
