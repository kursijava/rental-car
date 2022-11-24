package com.roi.rentalcar.database.entities;

import com.roi.rentalcar.static_data.Position;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String name;
    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Position position;
    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;
}
