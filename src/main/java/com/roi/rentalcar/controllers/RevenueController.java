package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.RevenueDTO;
import com.roi.rentalcar.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @GetMapping("/revenue/{id}")
    public ResponseEntity<RevenueDTO> getRevenueBYMonth(@PathVariable(name = "id") Long revenueId){
        return ResponseEntity.status(404).body(revenueService.getRevenueByRental(revenueId));
    }
}
