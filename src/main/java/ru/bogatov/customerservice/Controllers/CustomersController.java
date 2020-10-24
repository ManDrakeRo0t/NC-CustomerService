package ru.bogatov.customerservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.Entities.Customer;
import ru.bogatov.customerservice.Services.CustomerService;

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
    public void deleteById(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping()
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public void editCustomer(@RequestBody Customer customer, @PathVariable String id) {
        customerService.editCustomer(customer, id);
    }
}
