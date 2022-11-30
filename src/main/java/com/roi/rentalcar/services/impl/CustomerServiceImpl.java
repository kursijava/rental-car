package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Customer;
import com.roi.rentalcar.database.repositories.CustomerRepo;
import com.roi.rentalcar.dtos.CustomerDTO;
import com.roi.rentalcar.mappers.CustomerMapper;
import com.roi.rentalcar.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<CustomerDTO> getAll() {
        return customerMapper.toDtoList(customerRepo.findAll());
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Customer with id " + id + " does not exists"));
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() != null) throw new RuntimeException("Id must be null");
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public String deleteCustomer(Long id) {
        return null;
    }
}
