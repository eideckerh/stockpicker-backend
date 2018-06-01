package de.stockpicker.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @GetMapping(path = "/")
    public String test() {
        return "test";
    }
}
