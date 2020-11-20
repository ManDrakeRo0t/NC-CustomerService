package ru.bogatov.customerservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.bogatov.customerservice.entity.Customer;
import ru.bogatov.customerservice.service.CustomerService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private CustomerService customerService;

    public CustomUserDetailsService(@Autowired CustomerService customerService){
        this.customerService = customerService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(s);
        return CustomUserDetails.fromCustomerToUserDetails(customer);
    }

}
