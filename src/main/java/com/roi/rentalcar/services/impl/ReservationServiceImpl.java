package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Car;
import com.roi.rentalcar.database.entities.Customer;
import com.roi.rentalcar.database.entities.Refund;
import com.roi.rentalcar.database.entities.Reservation;
import com.roi.rentalcar.database.repositories.CarRepo;
import com.roi.rentalcar.database.repositories.RefundRepo;
import com.roi.rentalcar.database.repositories.ReservationRepo;
import com.roi.rentalcar.dtos.CarDTO;
import com.roi.rentalcar.dtos.RefundDTO;
import com.roi.rentalcar.dtos.ReservationDTO;
import com.roi.rentalcar.mappers.CarMapper;
import com.roi.rentalcar.mappers.ReservationMapper;
import com.roi.rentalcar.services.ReservationService;
import com.roi.rentalcar.static_data.CarStatus;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private RefundRepo refundRepo;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private CarMapper carMapper;
    @Override
    public ReservationDTO getById(Long id) {
        return null;
    }

    @Override
    public List<ReservationDTO> getAll() {
        return null;
    }

    @Override
    public ReservationDTO create(ReservationDTO dto) {
        if (dto.getReservationId() != null)
            throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        else {
            Reservation reservation = reservationMapper.toEntity(dto);
            reservation.setReservationBooking(LocalDate.now());
            List<Car> cars = carMapper.toEntityList(dto.getCars());
            reservation.setAmount(getAmmount(dto));
            reservation = reservationRepo.save(reservation);
            for (Car car: cars) {
                car.setReservation(reservation);
                carRepo.save(car);
            }
            ReservationDTO reservationDTO = reservationMapper.toDto(reservation);
            reservationDTO.setCars(dto.getCars());
            return reservationDTO;
        }
    }

    @Override
    public ReservationDTO update(ReservationDTO dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }


    @Override
    public String cancelReservation(Long id) {
        Reservation reservation = reservationRepo.findById(id).orElseThrow(()->
                new RuntimeException(StaticMessages.setIdNotFound(Reservation.class, id)));
        if (reservation.getReservationStart().isAfter(LocalDate.now().plusDays(2))){
            Refund refund = new Refund();
            refund.setReservation(reservation);
            refund.setRefund(reservation.getAmount() * 0.8);
            refund.setSurcharge(reservation.getAmount() * 0.2);
            refundRepo.save(refund);
            List<Car> cars = reservation.getCars();
            for (Car car: cars) {
                car.setCarStatus(CarStatus.AVAILABLE);
                car.setReservation(null);
                carRepo.save(car);
            }
            return "Reservation has been canceled";
        } else {
            return "Reservation must be canceled only 2 days before starting";
        }
    }

    private Double getAmmount(ReservationDTO reservation){
        Double ammount = 0.0;
        Long days = ChronoUnit.DAYS.between(reservation.getReservationStart(), reservation.getReservationEnd());
        for (CarDTO carDto: reservation.getCars()) {
            ammount = carDto.getAmount() * days;
            if (!reservation.getBookingBranch().getBranchId().equals(reservation.getReturnBranch().getBranchId()))
                ammount = ammount + (ammount * 0.1);
        }
        return ammount;
    }
}
