package com.roi.rentalcar.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BranchDTO {
    private Long branchId;
    private String name;
    private CityDTO city;
    private RentalDTO rental;
    private List<EmployeeDTO> employees;
    private List<CarDTO> cars;
    private List<EmployeeDTO> managers;
    private String warning;
}
