package com.enes.user.services.greeting;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "greeting-service")
interface GreetingClient {

    @RequestMapping(method = RequestMethod.POST, value = "/greeting", produces = "application/json")
    void greetUser(@RequestBody @Valid GreetingRequest request);
}
