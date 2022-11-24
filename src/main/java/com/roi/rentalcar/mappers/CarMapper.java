package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Car;
import com.roi.rentalcar.dtos.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CarMapper implements BaseMapper<Car, CarDTO> {

    @Override
    public CarDTO toDto(Car car) {
        return null;
    }

    @Override
    public Car toEntity(CarDTO carDTO) {
        return null;
    }

    @Override
    public List<CarDTO> toDtoList(List<Car> e) {
        return null;
    }

    @Override
    public List<Car> toEntityList(List<CarDTO> d) {
        return null;
    }
}
