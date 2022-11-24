package com.roi.rentalcar.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CityDTO {
    private String name;
    private List<BranchDTO> branches;
}
