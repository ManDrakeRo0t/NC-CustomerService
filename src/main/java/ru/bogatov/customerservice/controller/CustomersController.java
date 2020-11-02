package ru.bogatov.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.entity.Customer;
import ru.bogatov.customerservice.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomersController {
    private CustomerService customerService;

    public CustomersController(@Autowired CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<Customer> getAll() {
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
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping()
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            return ResponseEntity.ok().body(customerService.addCustomer(customer));
        }catch (RuntimeException e){
            return ResponseEntity.status(500).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer, @PathVariable String id) {
        try{
            return ResponseEntity.ok().body(customerService.editCustomer(customer, id));
        }catch (RuntimeException e){
            return ResponseEntity.status(500).build();
        }
    }
}
