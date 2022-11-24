package com.roi.rentalcar.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private String address;
    private List<ReservationDTO> reservations;
    private RentalDTO rental;
}
