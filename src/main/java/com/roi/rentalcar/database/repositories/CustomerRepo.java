package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "from Customer c where c.rental.rentalId = :id")
    List<Customer> findAllByRentalId(@Param("id") Long rentalId);
}
