package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Car;
import com.roi.rentalcar.database.entities.Reservation;
import com.roi.rentalcar.database.entities.UnavailableStatus;
import com.roi.rentalcar.database.repositories.BranchRepo;
import com.roi.rentalcar.database.repositories.CarRepo;
import com.roi.rentalcar.database.repositories.StatusRepo;
import com.roi.rentalcar.dtos.CarDTO;
import com.roi.rentalcar.mappers.BranchMapper;
import com.roi.rentalcar.mappers.CarMapper;
import com.roi.rentalcar.mappers.ReservationMapper;
import com.roi.rentalcar.mappers.UnavailableStatusMapper;
import com.roi.rentalcar.services.CarService;
import com.roi.rentalcar.static_data.CarStatus;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UnavailableStatusMapper unavailableStatusMapper;

    @Override
    public CarDTO getById(Long id) {
        Car car = carRepo.findById(id).
                orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(Car.class, id)));
        CarDTO carDTO = carMapper.toDto(car);
        carDTO = setOtherValues(car, carDTO);
        return carDTO;
    }

    @Override
    public List<CarDTO> getAll() {
        List<CarDTO> carDTOS = new ArrayList<>();
        carRepo.findAll().forEach(car -> {
            CarDTO carDTO = carMapper.toDto(car);
            carDTO = setOtherValues(car, carDTO);
            carDTOS.add(carDTO);
        });
        return carDTOS;
    }

    @Override
    public CarDTO create(CarDTO dto) {
        if (dto.getCarId() != null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        Car car = carMapper.toEntity(dto);
        if (dto.getBranch() != null) {
            Branch branch = branchRepo.findById(dto.getBranch().getBranchId()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(Branch.class, dto.getBranch().getBranchId())));
            car.setBranch(branch);
        }
        carRepo.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public CarDTO update(CarDTO dto) {
        if (dto.getCarId() == null) throw new RuntimeException(StaticMessages.IDNOTNULL.getMessage());
        Car car = carMapper.toEntity(dto);
        if (dto.getBranch() != null) {
            Branch branch = branchRepo.findById(dto.getBranch().getBranchId()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(Branch.class, dto.getBranch().getBranchId())));
            car.setBranch(branch);
        }
        if (dto.getStatus() != null) {
            UnavailableStatus status = statusRepo.findById(dto.getStatus().getStatusId()).
                    orElseThrow(() -> new RuntimeException(StaticMessages.setIdNotFound(UnavailableStatus.class, dto.getStatus().getStatusId())));
            car.setStatus(status);
        }
        car = carRepo.save(car);
        CarDTO carDTO = carMapper.toDto(car);
        carDTO = setOtherValues(car, carDTO);
        return carDTO;
    }

    @Override
    public String deleteById(Long id) {
        if (carRepo.findById(id).isEmpty())
            return StaticMessages.setIdNotFound(Car.class, id);
        carRepo.deleteById(id);
        return StaticMessages.deleted(Car.class, id);
    }
    @Override
    public List<CarDTO> getAllAvailableByDate(LocalDate startDate, LocalDate endDate) {
        List<Car> cars = carRepo.getAllByStatusNot(CarStatus.UNAVAILABLE);
        List<CarDTO> availableCars = new ArrayList<>();
        cars.forEach(car -> {
            if ((car.getReservation()==null ||
                    car.getReservation().getReservationEnd().isBefore(startDate) ||
                    car.getReservation().getReservationStart().isAfter(endDate))
                    && !car.getCarStatus().equals(CarStatus.UNAVAILABLE)){
                availableCars.add(carMapper.toDto(car));
            }
        });
        return availableCars;
    }

    private CarDTO setOtherValues(Car car, CarDTO carDTO) {
        if (car.getBranch() != null)
            carDTO.setBranch(branchMapper.toDto(car.getBranch()));
        if (car.getReservation() != null)
            carDTO.setReservation(reservationMapper.toDto(car.getReservation()));
        if (car.getStatus() != null)
            carDTO.setStatus(unavailableStatusMapper.toDto(car.getStatus()));
        return carDTO;
    }

//    cron = (seconds (0-59) minute (0-59)	hour (0 - 23)	day of the month (1 - 31)	month (1 - 12)	day of the week (0 - 6))
//  0 * * * * *	    Every minute
//  0 0 * * * *	    Every hour
//  30 10 0 * * *	    Every day at 12:10 AM
//  0 0 0 * * FRI	At 12:00 AM, only on Friday
//  0 0 1 24-31 * 4     At 12:00 AM, on day 1 of the month
    @Scheduled(cron = "${cron.time}")
//    @Scheduled(fixedRate = 1L)
//    @Scheduled(cron = "0 00 19 * * *")
    private void changeCarStatus() {
        List<Car> cars = carRepo.findAll();
        for (Car car : cars) {
            if (car.getReservation() != null) {
                Reservation reservation = car.getReservation();
                if (reservation.getReservationEnd().isBefore(LocalDate.now()) &&
                        car.getCarStatus().equals(CarStatus.BOOKED)){
                    car.setCarStatus(CarStatus.AVAILABLE);
                    car.setBranch(reservation.getReturnBranch());
                    carRepo.save(car);
                }
            }
        }
    }
}
