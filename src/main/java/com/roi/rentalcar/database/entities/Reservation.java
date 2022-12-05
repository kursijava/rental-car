package com.roi.rentalcar.database.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDate reservationBooking;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "booking_branch")
    private Branch bookingBranch;
    @ManyToOne
    @JoinColumn(name = "return_branch")
    private Branch returnBranch;
    @OneToMany(mappedBy = "reservation")
    private List<Car> cars;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @OneToOne(mappedBy = "reservation")
    private Refund refund;
}
