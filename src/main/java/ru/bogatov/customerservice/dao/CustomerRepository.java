package ru.bogatov.customerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogatov.customerservice.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    public Customer getCustomerById(UUID id);

    public Optional<Customer> findCustomerById(UUID id);
}
