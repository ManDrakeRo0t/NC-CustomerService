package ru.bogatov.customerservice.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogatov.customerservice.Entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    public Customer getCustomerById(UUID id);
}
