package ru.bogatov.customerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.customerservice.DAO.CustomerRepository;
import ru.bogatov.customerservice.DAO.PaidTypeRepository;
import ru.bogatov.customerservice.Entities.PaidType;

import java.util.List;
import java.util.UUID;

@Service
public class PaidTypeService {
    private PaidTypeRepository paidTypeRepository;
    private CustomerRepository customerRepository;

    public PaidTypeService(@Autowired PaidTypeRepository paidTypeRepository,
                           @Autowired CustomerRepository customerRepository){
        this.paidTypeRepository = paidTypeRepository;
        this.customerRepository = customerRepository;
    }

    public List<PaidType> getAll(){
        return paidTypeRepository.findAll();
    }

    public PaidType getOneById(String id){
        return paidTypeRepository.getPaidTypeById(UUID.fromString(id)); //todo проверка
    }

    public void deleteById(String id){
        UUID uuid = UUID.fromString(id);
        //todo проверка на наличие в офферах
        if(customerRepository.findAll().stream().anyMatch( c -> c.getPaidType().getId().equals(uuid))) throw new RuntimeException();
        paidTypeRepository.deletePaidTypeById(UUID.fromString(id));
    }

    public void editPaidType(PaidType paidType,String id){
        PaidType paidTypeFromDB = paidTypeRepository.getPaidTypeById(UUID.fromString(id));
        paidTypeFromDB.setName(paidType.getName());
        paidTypeRepository.save(paidTypeFromDB);
    }

    public void createPaidType(PaidType paidType){
        paidTypeRepository.save(paidType);
    }
}
