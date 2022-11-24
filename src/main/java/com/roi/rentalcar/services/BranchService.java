package com.roi.rentalcar.services;

import com.roi.rentalcar.dtos.BranchDTO;

import java.util.List;

public interface BranchService {

    BranchDTO getById(Long id);

    BranchDTO create(BranchDTO branchDTO);
    List<BranchDTO> getAll();
}
