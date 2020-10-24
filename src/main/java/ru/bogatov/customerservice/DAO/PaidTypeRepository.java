package ru.bogatov.customerservice.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogatov.customerservice.Entities.PaidType;

import java.util.UUID;

public interface PaidTypeRepository extends JpaRepository<PaidType, UUID> {
    public PaidType getPaidTypeById(UUID id);

    public void deletePaidTypeById(UUID id);
}
