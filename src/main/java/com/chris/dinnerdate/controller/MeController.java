package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.config.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    public record MeResponse(String email) {}

    @GetMapping("/me")
    public MeResponse getMe() {
        return new MeResponse(UserContext.getEmail());
    }

    @GetMapping("/hello")
    public String HEllo(@RequestParam String Name) {
        return "Hello " +Name+"!";
    }
}
