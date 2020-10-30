package ru.bogatov.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogatov.customerservice.dao.AddressesRepository;
import ru.bogatov.customerservice.dao.CustomerRepository;
import ru.bogatov.customerservice.dao.PaidTypeRepository;
import ru.bogatov.customerservice.entity.Customer;

import java.util.List;
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

    public void deleteCustomer(String id){
        Customer customer = customerRepository.getCustomerById(UUID.fromString(id));
        addressesRepository.delete(customer.getAddresses());
        customerRepository.delete(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer getOneById(String id){
        return customerRepository.getCustomerById(UUID.fromString(id));
    }

    public void addCustomer(Customer customer){
        if(customerRepository.findAll().stream().anyMatch(c -> c.getPhone().equals(customer.getPhone()))) return;
        if(customer.getAddresses() != null ) createAddress(customer);
        customerRepository.save(customer);
    }

    public void editCustomer(Customer customer,String id){
        Customer customerFromBD = customerRepository.getCustomerById(UUID.fromString(id));
        updateCustomer(customerFromBD,customer);
        customerRepository.save(customerFromBD);
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

}
