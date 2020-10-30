package ru.bogatov.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.dto.CustomersListDto;
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
    public CustomersListDto getAll() {
        CustomersListDto customersListDto = new CustomersListDto();
        customersListDto.setCustomers(customerService.getAll());
        return customersListDto;
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
