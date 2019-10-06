package com.enes.user.services.greeting;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingClient client;

    public GreetingService(GreetingClient client) {
        this.client = client;
    }

    public void greetUser(String name) {
        client.greetUser(new GreetingRequest(name));
    }
}
