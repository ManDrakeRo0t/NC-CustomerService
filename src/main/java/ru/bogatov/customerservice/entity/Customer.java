package ru.bogatov.customerservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique=true,nullable = false)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Addresses addresses;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "customers_paidtypes",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "paidtype_id") }
    )
    private List<PaidType> paidTypes; //todo list paid type

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Customer(){}

    public void update(Customer customer){
        if(customer.firstname != null) this.firstname= customer.firstname;
        if(customer.lastname != null) this.lastname = customer.lastname;
        if(customer.email != null) this.email = customer.email;
        if(customer.password != null) this.password = customer.password;
        if(customer.phone != null) this.phone = customer.phone;
     }



}
