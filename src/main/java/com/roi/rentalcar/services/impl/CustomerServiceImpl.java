package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Customer;
import com.roi.rentalcar.database.repositories.CustomerRepo;
import com.roi.rentalcar.dtos.CustomerDTO;
import com.roi.rentalcar.mappers.CustomerMapper;
import com.roi.rentalcar.services.CustomerService;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
                () -> new RuntimeException(StaticMessages.setIdNotFound(Customer.class, id)));
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() != null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }
}
