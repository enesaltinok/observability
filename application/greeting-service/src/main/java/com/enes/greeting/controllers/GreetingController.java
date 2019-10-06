package com.enes.greeting.controllers;

import com.enes.greeting.model.GreetingRequest;
import com.enes.greeting.services.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public ResponseEntity greet(@RequestBody @Valid GreetingRequest request) {
        greetingService.greet(request.getName());
        return ResponseEntity.ok().build();
    }
}
