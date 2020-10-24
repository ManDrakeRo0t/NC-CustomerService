package ru.bogatov.customerservice.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "paid_type")
public class PaidType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @OneToMany( fetch = FetchType.EAGER)
    private List<Customer> customersList;

    public PaidType(){}
}
