package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.City;
import com.roi.rentalcar.database.repositories.CityRepo;
import com.roi.rentalcar.dtos.CityDTO;
import com.roi.rentalcar.mappers.BranchMapper;
import com.roi.rentalcar.mappers.CityMapper;
import com.roi.rentalcar.services.CityService;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private BranchMapper branchMapper;
    @Override
    public CityDTO getById(String id) {
        City city = cityRepo.findById(id).orElseThrow(
                ()-> new RuntimeException(StaticMessages.setIdNotFound(City.class, id))
        );
        CityDTO cityDTO = cityMapper.toDto(city);
        cityDTO = setOtherValues(city,cityDTO);
        return cityDTO;
    }

    @Override
    public List<CityDTO> getAll() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        cityRepo.findAll().forEach(city -> {
            CityDTO cityDTO = cityMapper.toDto(city);
            cityDTO = setOtherValues(city, cityDTO);
            cityDTOS.add(cityDTO);
        });
        return cityDTOS;
    }

    @Override
    public CityDTO create(CityDTO dto) {
        if (dto.getName()!=null) throw new RuntimeException(StaticMessages.IDNOTNULL.getMessage());
        if (cityRepo.existsByNameIgnoreCase(dto.getName()))
            throw new RuntimeException(StaticMessages.setNameExists(City.class, dto.getName()));
        City city = cityMapper.toEntity(dto);
        city = cityRepo.save(city);
        return cityMapper.toDto(city);
    }

    @Override
    public CityDTO update(CityDTO dto) {
        return null;
    }

    @Override
    public String deleteById(String id) {
        if (!cityRepo.existsById(id)) return StaticMessages.setIdNotFound(City.class, id);
        cityRepo.deleteById(id);
        return StaticMessages.deleted(City.class, id);
    }

    private CityDTO setOtherValues(City city, CityDTO cityDTO){
        if (city.getBranches()!= null)
            cityDTO.setBranches(branchMapper.toDtoList(city.getBranches()));
        return cityDTO;
    }
}
