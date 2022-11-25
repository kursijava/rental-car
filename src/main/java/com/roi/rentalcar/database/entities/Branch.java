package com.roi.rentalcar.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "city")
    private City city;
    @ManyToOne
    @JoinColumn(name = "rental")
    private Rental rental;
    @OneToMany(mappedBy = "branch")
    private List<Employee> employees;
    @OneToMany(mappedBy = "branch")
    private List<Car> cars;
}
