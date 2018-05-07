package com.test.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class UserController {

    @RequestMapping("/com/test/user")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}