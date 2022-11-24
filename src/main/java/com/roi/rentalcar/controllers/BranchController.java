package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public BranchDTO getByID(@RequestParam Long id){
        return branchService.getById(id);
    }
    @PostMapping
    public BranchDTO create(@RequestBody BranchDTO branchDTO){
        return branchService.create(branchDTO);
    }
    @GetMapping("/all")
    public List<BranchDTO> getAll(){
        return branchService.getAll();
    }
}
