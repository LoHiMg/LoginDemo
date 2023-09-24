package com.kir.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/public")
    public String getPublicString() {
        return "It is public.";
    }

    @GetMapping("/private")
    public String getPrivateString(Principal principal) {
        return "It is private.";
    }
}
