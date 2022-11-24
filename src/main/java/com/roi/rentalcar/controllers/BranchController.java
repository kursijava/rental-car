package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public BranchDTO create(@RequestBody BranchDTO branchDTO){
        return branchService.create(branchDTO);
    }
    @GetMapping("/all")
    public List<BranchDTO> getAll(){
        return branchService.getAll();
    }
}
