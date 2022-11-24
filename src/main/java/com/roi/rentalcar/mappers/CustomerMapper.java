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
        if (customerDTO == null) return null;
        Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        return customer;
    }

    @Override
    public List<CustomerDTO> toDtoList(List<Customer> e) {
       if (e == null) return null;
       return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Customer> toEntityList(List<CustomerDTO> d) {
       if (d == null) return null;
       return d.stream().map(this::toEntity).toList();
    }
}
