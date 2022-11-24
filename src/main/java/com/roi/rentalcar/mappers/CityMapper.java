package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.City;
import com.roi.rentalcar.dtos.CityDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CityMapper implements BaseMapper<City, CityDTO>{
    @Override
    public CityDTO toDto(City city) {
        return null;
    }

    @Override
    public City toEntity(CityDTO cityDTO) {
        return null;
    }

    @Override
    public List<CityDTO> toDtoList(List<City> e) {
        return null;
    }

    @Override
    public List<City> toEntityList(List<CityDTO> d) {
        return null;
    }
}
