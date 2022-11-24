package com.roi.rentalcar.database.entities;

import com.roi.rentalcar.static_data.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class UnavailableStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDate date;
}
