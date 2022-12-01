package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Car;
import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.dtos.CarDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CarMapper implements BaseMapper<Car, CarDTO> {

    @Override
    public CarDTO toDto(Car car) {
        if (car == null)
            return null;
        else {
            CarDTO carDTO = new CarDTO();
            carDTO.setCarId(car.getCarId());
            carDTO.setCarStatus(car.getCarStatus());
            carDTO.setAmount(car.getAmount());
            carDTO.setBodyType(car.getBodyType());
            carDTO.setColor(car.getColor());
            carDTO.setBrand(car.getBrand());
            carDTO.setMileage(car.getMileage());
            carDTO.setYear(car.getYear());
            if (car.getBranch()!=null){
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setBranchId(car.getBranch().getBranchId());
                branchDTO.setName(car.getBranch().getName());
            }
            return carDTO;
        }
    }

    @Override
    public Car toEntity(CarDTO carDTO) {
       if (carDTO == null)
           return null;
       else {
           Car car = Car.builder()
                   .carStatus(carDTO.getCarStatus())
                   .bodyType(carDTO.getBodyType())
                   .amount(carDTO.getAmount())
                   .carId(carDTO.getCarId())
                   .brand(carDTO.getBrand())
                   .year(carDTO.getYear())
                   .color(carDTO.getColor())
                   .mileage(carDTO.getMileage())
                   .build();
           return car;
       }
    }

    @Override
    public List<CarDTO> toDtoList(List<Car> e) {
        if (e == null) return null;
        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car car: e) {
           carDTOS.add(toDto(car));
        }
        return carDTOS;
    }

//    @Override
//    public List<CarDTO> toDtoList(List<Car> e) {
//        if (e == null) return null;
//        return e.stream().map(this::toDto).toList();
//    }

    @Override
    public List<Car> toEntityList(List<CarDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
