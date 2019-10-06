package com.enes.user.model;

public class CreateUserResponse {

    private final String id;

    public CreateUserResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
