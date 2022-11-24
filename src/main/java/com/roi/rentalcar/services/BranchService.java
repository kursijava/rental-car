package com.roi.rentalcar.services;

import com.roi.rentalcar.dtos.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO create(BranchDTO branchDTO);
    List<BranchDTO> getAll();
}
