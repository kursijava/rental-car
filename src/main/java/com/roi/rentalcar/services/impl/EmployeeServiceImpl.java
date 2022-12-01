package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Employee;
import com.roi.rentalcar.database.repositories.BranchRepo;
import com.roi.rentalcar.database.repositories.EmployeeRepo;
import com.roi.rentalcar.dtos.EmployeeDTO;
import com.roi.rentalcar.mappers.BranchMapper;
import com.roi.rentalcar.mappers.EmployeeMapper;
import com.roi.rentalcar.services.EmployeeService;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private BranchMapper branchMapper;
    @Override
    public EmployeeDTO getById(Long id) {
        Employee employee = employeeRepo.findById(id).
                orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Employee.class, id)));
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        employeeDTO = setOtherValues(employee, employeeDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employeeRepo.findAll().forEach(employee -> {
            EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
            employeeDTO = setOtherValues(employee,employeeDTO);
            employeeDTOS.add(employeeDTO);
        });
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        if (dto.getEmployeeId()!= null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        Employee employee = employeeMapper.toEntity(dto);
        if (dto.getBranch()!=null){
            Branch branch = branchRepo.findById(dto.getBranch().getBranchId()).
                    orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Branch.class, dto.getBranch().getBranchId())));
            employee.setBranch(branch);
        }
        employee = employeeRepo.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO dto) {
        if (dto.getEmployeeId()== null) throw new RuntimeException(StaticMessages.IDNOTNULL.getMessage());
        Employee employee = employeeRepo.findById(dto.getEmployeeId()).
                orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Employee.class, dto.getEmployeeId())));
        employee.setName(dto.getName());
        employee.setPosition(dto.getPosition());
        if (dto.getBranch()!=null){
            Branch branch = branchRepo.findById(dto.getBranch().getBranchId()).
                    orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Branch.class, dto.getBranch().getBranchId())));
            employee.setBranch(branch);
        }
        employeeRepo.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public String deleteById(Long id) {
        if (!employeeRepo.existsById(id)) return StaticMessages.setIdNotFound(Employee.class, id);
        employeeRepo.deleteById(id);
        return StaticMessages.deleted(Employee.class, id);
    }

    private EmployeeDTO setOtherValues(Employee employee, EmployeeDTO employeeDTO){
        if (employee.getBranch()!=null)
            employeeDTO.setBranch(branchMapper.toDto(employee.getBranch()));
        return employeeDTO;
    }
}
