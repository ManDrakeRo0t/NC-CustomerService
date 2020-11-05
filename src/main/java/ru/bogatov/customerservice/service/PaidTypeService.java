package ru.bogatov.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.customerservice.dao.CustomerRepository;
import ru.bogatov.customerservice.dao.PaidTypeRepository;
import ru.bogatov.customerservice.entity.PaidType;

import java.util.List;
import java.util.Optional;
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

    public void deleteById(String id) throws RuntimeException{
        UUID uuid = UUID.fromString(id);
        if(customerRepository.findAll().stream().anyMatch( c -> c.getPaidType().getId().equals(uuid))) throw new RuntimeException("cant delete this PaidType");
        paidTypeRepository.deletePaidTypeById(UUID.fromString(id));
    }

    public PaidType editPaidType(PaidType paidType,String id) throws RuntimeException{
        Optional<PaidType> OptionalTypeFromDB = paidTypeRepository.findPaidTypeById(UUID.fromString(id));
        if(!OptionalTypeFromDB.isPresent()){
            throw new RuntimeException("cant find paidType with id : " + id);
        }else{
            PaidType paidTypeFromDB = OptionalTypeFromDB.get();
            paidTypeFromDB.setName(paidType.getName());
            return paidTypeRepository.save(paidTypeFromDB);
        }
    }

    public PaidType createPaidType(PaidType paidType){
        return paidTypeRepository.save(paidType);
    }
}
