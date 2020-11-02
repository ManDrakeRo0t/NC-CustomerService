package ru.bogatov.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogatov.customerservice.dao.AddressesRepository;
import ru.bogatov.customerservice.dao.CustomerRepository;
import ru.bogatov.customerservice.dao.PaidTypeRepository;
import ru.bogatov.customerservice.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {

    CustomerRepository customerRepository;
    AddressesRepository addressesRepository;
    PaidTypeRepository paidTypeRepository;

    public CustomerService(@Autowired CustomerRepository customerRepository,
                           @Autowired AddressesRepository addressesRepository,
                           @Autowired PaidTypeRepository paidTypeRepository){
        this.customerRepository = customerRepository;
        this.addressesRepository = addressesRepository;
        this.paidTypeRepository = paidTypeRepository;
    }

    public void deleteCustomer(String id) throws RuntimeException{
        try{
            Customer customer = findCustomerById(id);
            addressesRepository.delete(customer.getAddresses());
            customerRepository.delete(customer);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer getOneById(String id){
        return customerRepository.getCustomerById(UUID.fromString(id));
    }

    public Customer addCustomer(Customer customer) throws RuntimeException{
        if(customerRepository.findAll().stream().anyMatch(c -> c.getPhone().equals(customer.getPhone()))) throw new RuntimeException("this phone already used");
        if(customer.getAddresses() != null ) createAddress(customer);
        return customerRepository.save(customer);
    }

    public Customer editCustomer(Customer customer,String id) throws RuntimeException{
        try{
            Customer customerFromBD = findCustomerById(id);
            updateCustomer(customerFromBD,customer);
            return customerRepository.save(customerFromBD);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateCustomer(Customer oldCustomer,Customer newCustomer){
        if(newCustomer.getAddresses() != null){
            addressesRepository.deleteAddressesById(oldCustomer.getAddresses().getId());
            createAddress(newCustomer);
            oldCustomer.setAddresses(newCustomer.getAddresses());
        }
        oldCustomer.update(newCustomer);
    }

    public void createAddress(Customer customer){
        customer.setAddresses(addressesRepository.save(customer.getAddresses()));
    }

    public Customer findCustomerById(String id) throws RuntimeException{
        Optional<Customer> optionalCustomer = customerRepository.findCustomerById(UUID.fromString(id));
        if(!optionalCustomer.isPresent()){
            throw new RuntimeException("cant find customer with id : " + id);
        }else{
            return optionalCustomer.get();
        }
    }

}
