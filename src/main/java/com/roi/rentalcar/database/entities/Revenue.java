package com.roi.rentalcar.database.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revenueId;
    private String month;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "rental")
    private Rental rental;
}
