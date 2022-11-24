package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Employee;
import com.roi.rentalcar.dtos.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper implements BaseMapper<Employee, EmployeeDTO> {
    @Override
    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) return null;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setPosition(employee.getPosition());
        return employeeDTO;
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) return null;
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setPosition(employeeDTO.getPosition());
        return employee;
    }

    @Override
    public List<EmployeeDTO> toDtoList(List<Employee> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Employee> toEntityList(List<EmployeeDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
