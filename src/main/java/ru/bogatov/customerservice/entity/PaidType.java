package ru.bogatov.customerservice.entity;

import lombok.Getter;
import lombok.Setter;

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
    @Transient
    @ManyToMany(mappedBy = "paidTypes")
    List<Customer> customerList;

    public PaidType(){}
}
