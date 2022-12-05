package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.ReservationDTO;
import com.roi.rentalcar.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationDTO create(@Valid @RequestBody ReservationDTO reservationDTO){
        return reservationService.create(reservationDTO);
    }
}
