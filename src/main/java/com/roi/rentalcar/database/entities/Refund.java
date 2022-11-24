package com.roi.rentalcar.database.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;
    private Double surcharge;
    private Double refund;
    private LocalDate returnDate;
    private String comment;
    @OneToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;
}
