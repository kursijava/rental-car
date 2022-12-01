package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.repositories.BranchRepo;
import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.mappers.BranchMapper;
import com.roi.rentalcar.mappers.CarMapper;
import com.roi.rentalcar.services.BranchService;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public BranchDTO getById(Long id) {
        Optional<Branch> optionalBranch = branchRepo.findById(id);
        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            BranchDTO branchDTO = branchMapper.toDto(branch);
            if (!branch.getCars().isEmpty())
                branchDTO.setCars(carMapper.toDtoList(branch.getCars()));
            return branchDTO;
        } else throw new RuntimeException(StaticMessages.setIdNotFound(Branch.class, id));
    }
    @Override
    public BranchDTO create(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() != null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        if (branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName())){
            throw new RuntimeException();
        }
        Branch branch = branchMapper.toEntity(branchDTO);
        branch = branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }

    @Override
    public List<BranchDTO> getAll() {
        List<Branch> branches = branchRepo.findAll();
        return branchMapper.toDtoList(branches);
    }

    @Override
    public String deleteById(Long id) {
        try {
            if (branchRepo.findById(id).isEmpty())
                return StaticMessages.setIdNotFound(Branch.class, id);
            branchRepo.deleteById(id);
                return StaticMessages.deleted(Branch.class, id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public BranchDTO update(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() == null) throw new RuntimeException(StaticMessages.IDNOTNULL.getMessage());
        Branch branch = branchRepo.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(Branch.class, branchDTO.getBranchId())));
        if (!branch.getName().toUpperCase().equals(branchDTO.getName().toUpperCase())
                && branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName()))
            throw new RuntimeException(StaticMessages.setNameExists(Branch.class, branchDTO.getName()));
        branch.setName(branchDTO.getName());
        branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }
}
