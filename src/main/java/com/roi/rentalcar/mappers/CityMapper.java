package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.City;
import com.roi.rentalcar.dtos.CityDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CityMapper implements BaseMapper<City, CityDTO>{
    @Override
    public CityDTO toDto(City city) {
        if (city == null) return null;
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(city.getName());
        return cityDTO;
    }

    @Override
    public City toEntity(CityDTO cityDTO) {
        if (cityDTO == null) return null;
        City city = new City();
        city.setName(cityDTO.getName());
        return city;
    }

    @Override
    public List<CityDTO> toDtoList(List<City> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<City> toEntityList(List<CityDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
