package ru.bogatov.customerservice.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.bogatov.customerservice.entity.Customer;
import ru.bogatov.customerservice.entity.Role;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private Set<Role> grantedAuthorities;

    public static CustomUserDetails fromCustomerToUserDetails(Customer customer){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.login = customer.getEmail();
        customUserDetails.password = customer.getPassword();
        customUserDetails.grantedAuthorities = customer.getRoles();
        return customUserDetails;
    }

    public static CustomUserDetails fromM2tToken(){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.login = "M2M";
        customUserDetails.grantedAuthorities.add(Role.USER);
        customUserDetails.grantedAuthorities.add(Role.ADMIN);
        return customUserDetails;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
