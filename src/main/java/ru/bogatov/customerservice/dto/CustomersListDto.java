package ru.bogatov.customerservice.dto;

import lombok.Data;
import ru.bogatov.customerservice.entity.Customer;

import java.util.List;
@Data
public class CustomersListDto {
    public List<Customer> customers;
}
