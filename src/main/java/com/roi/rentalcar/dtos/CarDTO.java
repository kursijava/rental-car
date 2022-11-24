package com.roi.rentalcar.dtos;

import com.roi.rentalcar.static_data.CarStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class CarDTO {
    private Long carId;
    private String brand;
    private String bodyType;
    private Integer year;
    private String color;
    private Double mileage;
    private Double amount;
    private CarStatus carStatus;
    private UnavaibleStatusDTO status;
    private ReservationDTO reservation;
    private BranchDTO branch;
}
