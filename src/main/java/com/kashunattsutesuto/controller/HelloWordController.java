package com.kashunattsutesuto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    @GetMapping("/")
    public String helloWord() {
        return "Hello World";
    }
}
