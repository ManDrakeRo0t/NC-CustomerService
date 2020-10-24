package ru.bogatov.customerservice.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogatov.customerservice.Entities.Addresses;

import java.util.UUID;

public interface AddressesRepository extends JpaRepository<Addresses, UUID> {
    public void deleteAddressesById(UUID Id);
}
