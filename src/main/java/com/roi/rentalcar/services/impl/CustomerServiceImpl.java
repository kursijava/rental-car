package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Customer;
import com.roi.rentalcar.database.repositories.CustomerRepo;
import com.roi.rentalcar.dtos.CustomerDTO;
import com.roi.rentalcar.mappers.CustomerMapper;
import com.roi.rentalcar.mappers.RentalMapper;
import com.roi.rentalcar.mappers.ReservationMapper;
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
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Override
    public List<CustomerDTO> getAll() {
        return customerMapper.toDtoList(customerRepo.findAll());
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(
                () -> new RuntimeException(StaticMessages.setIdNotFound(Customer.class, id)));
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        customerDTO = setOtherValues(customer, customerDTO);
        return customerDTO;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() != null) throw new RuntimeException(StaticMessages.IDNULL.getMessage());
        Customer customer = customerMapper.toEntity(customerDTO);
        if (customerDTO.getRental()!=null)
            customer.setRental(rentalMapper.toEntity(customerDTO.getRental()));
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() == null) throw new RuntimeException(StaticMessages.IDNOTNULL.getMessage());
        Customer customer = customerRepo.findById(customerDTO.getCustomerId()).
                orElseThrow(()-> new RuntimeException(StaticMessages.setIdNotFound(Customer.class, customerDTO.getCustomerId())));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public String deleteById(Long id) {
        if (!customerRepo.existsById(id)) return StaticMessages.setIdNotFound(Customer.class, id);
        customerRepo.deleteById(id);
        return StaticMessages.deleted(Customer.class, id);
    }

    private CustomerDTO setOtherValues(Customer customer, CustomerDTO customerDTO){
        if (customer.getRental()!= null)
            customerDTO.setRental(rentalMapper.toDto(customer.getRental()));
        if (customer.getReservations()!=null)
            customerDTO.setReservations(reservationMapper.toDtoList(customer.getReservations()));
        return customerDTO;
    }
}
