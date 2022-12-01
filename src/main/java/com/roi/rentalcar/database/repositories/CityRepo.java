package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, String> {
    Boolean existsByNameIgnoreCase(String name);
}
