package com.enes.user.services.greeting;

import javax.validation.constraints.NotBlank;

class GreetingRequest {

    @NotBlank
    private String name;

    public GreetingRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
