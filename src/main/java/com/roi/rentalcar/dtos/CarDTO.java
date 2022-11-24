package com.roi.rentalcar.dtos;

import com.roi.rentalcar.static_data.CarStatus;
import lombok.Data;

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
    private UnavailableStatusDTO status;
    private ReservationDTO reservation;
    private BranchDTO branch;
}
