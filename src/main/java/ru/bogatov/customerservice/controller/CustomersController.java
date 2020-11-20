package ru.bogatov.customerservice.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.entity.Customer;
import ru.bogatov.customerservice.entity.PaidType;
import ru.bogatov.customerservice.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("customers")
@PreAuthorize("hasAnyAuthority('USER')")
public class CustomersController {
    private CustomerService customerService;

    public CustomersController(@Autowired CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<Customer> getAll(HttpServletRequest request){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable String id) {
        return customerService.getOneById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("was deleted");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            return ResponseEntity.ok().body(customerService.addCustomer(customer));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/paid-types")
    public List<PaidType> getCustomersPaidTypes(@PathVariable String id){
        return customerService.getCustomersPaidTypes(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer, @PathVariable String id) {
        try{
            return ResponseEntity.ok().body(customerService.editCustomer(customer, id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
