package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Car;
import com.roi.rentalcar.database.repositories.BranchRepo;
import com.roi.rentalcar.database.repositories.CarRepo;
import com.roi.rentalcar.dtos.CarDTO;
import com.roi.rentalcar.mappers.CarMapper;
import com.roi.rentalcar.services.CarService;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private CarMapper carMapper;
    @Override
    public CarDTO getById(Long id) {
        Car car = carRepo.findById(id).
                orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Car.class, id)));
        return carMapper.toDto(car);
    }

    @Override
    public List<CarDTO> getAll() {
        return carMapper.toDtoList(carRepo.findAll());
    }

    @Override
    public CarDTO create(CarDTO dto) {
        if (dto.getCarId() != null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        Car car = carMapper.toEntity(dto);
        if (dto.getBranch() != null) {
            Branch branch = branchRepo.findById(dto.getBranch().getBranchId()).
                    orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Branch.class, dto.getBranch().getBranchId())));
            car.setBranch(branch);
        }
        carRepo.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public CarDTO update(CarDTO dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }
}
