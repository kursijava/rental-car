package com.roi.rentalcar.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Must not be null")
    private List<CarDTO> cars;
    private CustomerDTO customer;
    private RefundDTO refund;
}
