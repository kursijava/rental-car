package com.roi.rentalcar.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationDTO {
    private Long reservationId;
    private LocalDate reservationBooking;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private Double amount;
    private BranchDTO bookingBranch;
    private BranchDTO returnBranch;
    private List<CarDTO> cars;
    private CustomerDTO customer;
    private RefundDTO refund;
}
