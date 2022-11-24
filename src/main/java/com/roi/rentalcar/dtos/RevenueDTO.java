package com.roi.rentalcar.dtos;

import lombok.Data;

@Data
public class RevenueDTO {
    private Long revenueId;
    private String month;
    private Double amount;
}
