package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Reservation;
import com.roi.rentalcar.dtos.ReservationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationMapper implements BaseMapper<Reservation, ReservationDTO> {
    @Override
    public ReservationDTO toDto(Reservation reservation) {
        if (reservation == null) return null;
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setAmount(reservation.getAmount());
        reservationDTO.setReservationBooking(reservation.getReservationBooking());
        reservationDTO.setReservationEnd(reservation.getReservationEnd());
        return reservationDTO;
    }

    @Override
    public Reservation toEntity(ReservationDTO reservationDTO) {
        if (reservationDTO == null) return null;
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setAmount(reservationDTO.getAmount());
        reservation.setReservationBooking(reservationDTO.getReservationBooking());
        reservation.setReservationEnd(reservationDTO.getReservationEnd());
        return reservation;
    }

    @Override
    public List<ReservationDTO> toDtoList(List<Reservation> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Reservation> toEntityList(List<ReservationDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
