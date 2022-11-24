package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {
}
