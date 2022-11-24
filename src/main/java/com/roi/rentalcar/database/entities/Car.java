package com.roi.rentalcar.database.entities;

import com.roi.rentalcar.static_data.CarStatus;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;
    private String bodyType;
    private Integer year;
    private String color;
    private Double mileage;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private CarStatus carStatus;
    @OneToOne
    private UnavailableStatus status;
    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;
}
