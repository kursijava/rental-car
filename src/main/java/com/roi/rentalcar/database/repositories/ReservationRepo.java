package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Query(value = "select r from Reservation r where r.bookingBranch = ?1 and month(r.reservationStart) = ?2")
    List<Reservation> getAllBranchAndMonth(Branch branch, Integer month);
}
