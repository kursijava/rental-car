package com.roi.rentalcar.services;

import com.roi.rentalcar.dtos.ReservationDTO;

import java.time.LocalDate;

public interface ReservationService extends CrudService<ReservationDTO, Long> {
    String cancelReservation(Long id);
    String returnCar(Long reservationId, LocalDate returnDate, Long carId, String status);
}
