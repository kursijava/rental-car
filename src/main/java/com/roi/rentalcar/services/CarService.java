package com.roi.rentalcar.services;

import com.roi.rentalcar.dtos.CarDTO;

import java.time.LocalDate;
import java.util.List;

public interface CarService extends CrudService<CarDTO, Long>{
    List<CarDTO> getAllAvailableByDate(LocalDate startDate, LocalDate endDate);
}
