package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.repositories.BranchRepo;
import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.mappers.BranchMapper;
import com.roi.rentalcar.mappers.CarMapper;
import com.roi.rentalcar.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        } else throw new RuntimeException("Branch with id ".concat(id.toString()).concat(" does not exist"));
    }
    @Override
    public BranchDTO create(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() != null) throw new RuntimeException("Id must be null");
        if (branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName())){
            throw new RuntimeException("Branch with name "+ branchDTO.getName() +" already exists");
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
                return "Branch with id ".concat(id.toString()).concat(" does not exist");
            branchRepo.deleteById(id);
                return "Branch with " + id + " has ben deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public BranchDTO updateBranch(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() == null) throw new RuntimeException("Id must not be null");
        Branch branch = branchRepo.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch with id " + branchDTO.getBranchId() + " does not exists"));
        if (!branch.getName().toUpperCase().equals(branchDTO.getName().toUpperCase())
                && branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName()))
            throw new RuntimeException("Branch with name " + branchDTO.getName() + " already exists");
        branch.setName(branchDTO.getName());
        branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }
}
