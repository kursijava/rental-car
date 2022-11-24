package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Customer;
import com.roi.rentalcar.dtos.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomerMapper implements BaseMapper<Customer, CustomerDTO>{
    @Override
    public CustomerDTO toDto(Customer customer) {
        if (customer == null) return null;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public List<CustomerDTO> toDtoList(List<Customer> e) {
        return null;
    }

    @Override
    public List<Customer> toEntityList(List<CustomerDTO> d) {
        return null;
    }
}
