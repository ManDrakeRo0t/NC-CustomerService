package ru.bogatov.customerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogatov.customerservice.DAO.AddressesRepository;
import ru.bogatov.customerservice.DAO.CustomerRepository;
import ru.bogatov.customerservice.DAO.PaidTypeRepository;
import ru.bogatov.customerservice.Entities.Addresses;
import ru.bogatov.customerservice.Entities.Customer;
import ru.bogatov.customerservice.Entities.PaidType;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {

    CustomerRepository customerRepo;
    AddressesRepository addressesRepository;
    PaidTypeRepository paidTypeRepository;

    public CustomerService(@Autowired CustomerRepository customerRepo,
                           @Autowired AddressesRepository addressesRepository,
                           @Autowired PaidTypeRepository paidTypeRepository){
        this.customerRepo = customerRepo;
        this.addressesRepository = addressesRepository;
        this.paidTypeRepository = paidTypeRepository;
    }

    public void deleteCustomer(String id){
        Customer customer = customerRepo.getCustomerById(UUID.fromString(id));
        addressesRepository.delete(customer.getAddresses());
        customerRepo.delete(customer);
    }

    public List<Customer> getAll(){
        return customerRepo.findAll();
    }

    public Customer getOneById(String id){
        return customerRepo.getCustomerById(UUID.fromString(id));
    }

    public void addCustomer(Customer customer){
        if(customerRepo.findAll().stream().anyMatch(c -> c.getPhone().equals(customer.getPhone()))) return;
        if(customer.getAddresses() != null) createAddress(customer);
        if(customer.getPaidType() != null )createPaidType(customer);
        customerRepo.save(customer);
    }

    public void editCustomer(Customer customer,String id){
        Customer customerFromBD = customerRepo.getCustomerById(UUID.fromString(id));
        updateCustomer(customerFromBD,customer);
        customerRepo.save(customerFromBD);
    }

    public void updateCustomer(Customer oldCustomer,Customer newCustomer){
        if(newCustomer.getAddresses() != null){
            addressesRepository.deleteAddressesById(oldCustomer.getAddresses().getId());
            createAddress(newCustomer);
            oldCustomer.setAddresses(newCustomer.getAddresses());
        }
        if(newCustomer.getPaidType() != null){
            createPaidType(newCustomer);
            oldCustomer.setPaidType(newCustomer.getPaidType());
        }
        oldCustomer.update(newCustomer);
    }

    public void createAddress(Customer customer){
        customer.setAddresses(addressesRepository.save(customer.getAddresses()));
    }

    public void createPaidType(Customer customer){
        customer.setPaidType(paidTypeRepository.save(customer.getPaidType()));
    }
}
