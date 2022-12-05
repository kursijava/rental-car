package com.roi.rentalcar.database.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    private String name;
    private String internetDomain;
    private String contactAddress;
    @OneToMany(mappedBy = "rental")
    private List<Branch> branches;
    @OneToMany(mappedBy = "rental", fetch = FetchType.EAGER)
    private List<Customer> customers;
}
