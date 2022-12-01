package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.CityDTO;
import com.roi.rentalcar.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    public List<CityDTO> getAll(){
        return cityService.getAll();
    }
    @GetMapping
    public CityDTO getById(@RequestParam String id){
        return cityService.getById(id);
    }
}
