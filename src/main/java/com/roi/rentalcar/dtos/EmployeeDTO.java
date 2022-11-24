package com.roi.rentalcar.dtos;

import com.roi.rentalcar.static_data.Position;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long employeeId;
    private String name;
    private Position position;
    private BranchDTO branch;
}
