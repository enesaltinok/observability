package com.enes.user.controllers;

import com.enes.user.model.CreateUserRequest;
import com.enes.user.model.CreateUserResponse;
import com.enes.user.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createNewUser(@RequestBody @Valid CreateUserRequest request) {
        String id = userService.create(request);
        return ResponseEntity.ok(new CreateUserResponse(id));
    }
}
