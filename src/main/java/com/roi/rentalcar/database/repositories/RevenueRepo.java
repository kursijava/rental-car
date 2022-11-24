package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepo extends JpaRepository<Revenue, Long> {
}
