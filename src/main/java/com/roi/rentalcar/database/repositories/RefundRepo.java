package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepo extends JpaRepository<Refund, Long> {
}
