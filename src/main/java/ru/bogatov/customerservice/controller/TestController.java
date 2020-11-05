package ru.bogatov.customerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/admin")
    public String admin(){
        return "Hello admin";
    }
    @GetMapping("/user")
    public String user(){
        return "Hello user";
    }
}
