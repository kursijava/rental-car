package com.roi.rentalcar.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RefundDTO {
    private Long refundId;
    private Double surcharge;
    private Double refund;
    private LocalDate returnDate;
    private String comment;
    private ReservationDTO reservation;
}
