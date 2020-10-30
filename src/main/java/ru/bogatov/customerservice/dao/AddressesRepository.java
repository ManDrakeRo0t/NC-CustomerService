package ru.bogatov.customerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogatov.customerservice.entity.Addresses;

import java.util.UUID;

public interface AddressesRepository extends JpaRepository<Addresses, UUID> {
    public void deleteAddressesById(UUID Id);
}
