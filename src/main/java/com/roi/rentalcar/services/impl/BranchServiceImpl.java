package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Car;
import com.roi.rentalcar.database.entities.City;
import com.roi.rentalcar.database.entities.Rental;
import com.roi.rentalcar.database.repositories.BranchRepo;
import com.roi.rentalcar.database.repositories.CityRepo;
import com.roi.rentalcar.database.repositories.RentalRepo;
import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.mappers.*;
import com.roi.rentalcar.services.BranchService;
import com.roi.rentalcar.static_data.CarStatus;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private EmployeeMapper employeeMapper;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private RentalRepo rentalRepo;


    @Override
    public BranchDTO getById(Long id) {
        Optional<Branch> optionalBranch = branchRepo.findById(id);
        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            BranchDTO branchDTO = branchMapper.toDto(branch);
            branchDTO = setOtherValues(branch, branchDTO);
            setWarning(branch, branchDTO);
            return branchDTO;
        } else throw new RuntimeException(StaticMessages.setIdNotFound(Branch.class, id));
    }

    @Override
    public BranchDTO create(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() != null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        if (branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName())) {
            throw new RuntimeException(StaticMessages.setNameExists(Branch.class, branchDTO.getName()));
        }
        Branch branch = branchMapper.toEntity(branchDTO);
        if (branchDTO.getCity() != null) {
            City city = cityRepo.findById(branchDTO.getCity().getName()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(City.class, branchDTO.getCity().getName())));
            branch.setCity(city);
        }
        if (branchDTO.getRental() != null) {
            Rental rental = rentalRepo.findById(branchDTO.getRental().getRentalId()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(Rental.class, branchDTO.getRental().getRentalId())));
            branch.setRental(rental);
        }
        branch = branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }

    @Override
    public List<BranchDTO> getAll() {
        List<BranchDTO> branchDTOS = new ArrayList<>();
        branchRepo.findAll().forEach(branch -> {
            BranchDTO branchDTO = branchMapper.toDto(branch);
            branchDTO = setOtherValues(branch, branchDTO);
            branchDTOS.add(branchDTO);
        });
        return branchDTOS;
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
        if (branchDTO.getCity() != null) {
            City city = cityRepo.findById(branchDTO.getCity().getName()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(City.class, branchDTO.getCity().getName())));
            branch.setCity(city);
        }
        if (branchDTO.getRental() != null) {
            Rental rental = rentalRepo.findById(branchDTO.getRental().getRentalId()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(Rental.class, branchDTO.getRental().getRentalId())));
            branch.setRental(rental);
        }
        branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }

    private BranchDTO setOtherValues(Branch branch, BranchDTO branchDTO) {
        if (!branch.getCars().isEmpty())
            branchDTO.setCars(carMapper.toDtoList(branch.getCars()));
        if (branch.getRental() != null)
            branchDTO.setRental(rentalMapper.toDto(branch.getRental()));
        if (!branch.getEmployees().isEmpty())
            branchDTO.setEmployees(employeeMapper.toDtoList(branch.getEmployees()));
        if (branch.getCity() != null)
            branchDTO.setCity(cityMapper.toDto(branch.getCity()));
        return branchDTO;
    }
    private void setWarning(Branch branch, BranchDTO branchDTO) {
        if (branch.getCars() != null && !branch.getCars().isEmpty()) {
            int available = 0;
            for (Car car : branch.getCars()) {
                if (car.getCarStatus().equals(CarStatus.AVAILABLE))
                    available++;
            }
            if (available < 3)
                branchDTO.setWarning("Warning! Lees than 2 cars are available today");
        }
    }

}
