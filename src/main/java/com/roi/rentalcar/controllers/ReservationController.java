package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.ReservationDTO;
import com.roi.rentalcar.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationDTO create(@Valid @RequestBody ReservationDTO reservationDTO){
        return reservationService.create(reservationDTO);
    }
    @GetMapping("/return")
    public String returnCar(@RequestParam Long reservationId,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  @RequestParam LocalDate returnDate,
                            @RequestParam(required = false) Long carId, @RequestParam(required = false) String status){
        return reservationService.returnCar(reservationId,returnDate,carId,status);
    }
}
