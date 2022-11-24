package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.dtos.BranchDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BranchMapper implements BaseMapper<Branch, BranchDTO>{

    @Override
    public BranchDTO toDto(Branch branch) {
        if (branch == null) return null;
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchId(branch.getBranchId());
        branchDTO.setName(branch.getName());
        return branchDTO;
    }

    @Override
    public Branch toEntity(BranchDTO branchDTO) {
        if (branchDTO == null) return null;
        Branch branch = new Branch();
        branch.setBranchId(branchDTO.getBranchId());
        branch.setName(branchDTO.getName());
        return branch;
    }

    @Override
    public List<BranchDTO> toDtoList(List<Branch> e) {
        if (e == null)
            return null;
        else
            return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Branch> toEntityList(List<BranchDTO> d) {
        if (d == null)
            return null;
        else
            return d.stream().map(this::toEntity).toList();
    }
}
