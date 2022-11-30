package com.roi.rentalcar.controllers;

import com.roi.rentalcar.dtos.BranchDTO;
import com.roi.rentalcar.services.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/")
    public BranchDTO getByID(@RequestParam Long id){
        return branchService.getById(id);
    }
    @PostMapping("/")
    public BranchDTO create(@RequestBody BranchDTO branchDTO){
        return branchService.create(branchDTO);
    }
    @GetMapping("/all")
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BranchDTO> getAll(){
        return branchService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id){
        return branchService.deleteById(id);
    }

    @PutMapping("/")
    public BranchDTO updateBranch(@RequestBody BranchDTO branchDTO){
        return branchService.updateBranch(branchDTO);
    }
}
