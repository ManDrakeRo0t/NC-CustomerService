package ru.bogatov.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.configuration.JwtProvider;
import ru.bogatov.customerservice.dto.LoginRequest;
import ru.bogatov.customerservice.entity.Customer;
import ru.bogatov.customerservice.service.CustomerService;

import java.util.UUID;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private CustomerService customerService;
    private JwtProvider jwtProvider;

    public AuthenticationController(@Autowired CustomerService customerService,
                                    @Autowired JwtProvider jwtProvider){
        this.customerService = customerService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("")
    public ResponseEntity<UUID> postIdFromToken(@RequestBody String token){
        try{
            return ResponseEntity.ok(jwtProvider.getIdFormToken(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
        try{
            return ResponseEntity.ok().body(customerService.addCustomer(customer));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        Customer customer = customerService.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());
        if(customer != null){
            String token = jwtProvider.generateToken(customer.getEmail(),customer.getPassword());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
