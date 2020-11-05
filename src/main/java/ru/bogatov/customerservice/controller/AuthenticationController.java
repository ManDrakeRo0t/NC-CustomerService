package ru.bogatov.customerservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bogatov.customerservice.service.CustomerService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private CustomerService customerService;


}
