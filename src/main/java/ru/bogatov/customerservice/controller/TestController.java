package ru.bogatov.customerservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Hello admin";
    }
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/user")
    public String user(){
        return "Hello user";
    }
}
