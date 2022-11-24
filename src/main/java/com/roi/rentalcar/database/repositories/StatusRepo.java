package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.UnavailableStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<UnavailableStatus, Long> {
}
