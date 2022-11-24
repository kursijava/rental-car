package com.roi.rentalcar.dtos;

import com.roi.rentalcar.static_data.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UnavaibleStatusDTO {
    private Long statusId;
    private Status status;
    private LocalDate date;
}
