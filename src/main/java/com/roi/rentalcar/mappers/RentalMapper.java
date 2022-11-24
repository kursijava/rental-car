package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Rental;
import com.roi.rentalcar.dtos.RentalDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalMapper implements BaseMapper<Rental, RentalDTO> {
    @Override
    public RentalDTO toDto(Rental rental) {
        if (rental == null) return null;
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setRentalId(rental.getRentalId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setInternetDomain(rental.getInternetDomain());
        rentalDTO.setContactAddress(rental.getContactAddress());
        return rentalDTO;
    }

    @Override
    public Rental toEntity(RentalDTO rentalDTO) {
        if (rentalDTO == null) return null;
        Rental rental = new Rental();
        rental.setRentalId(rentalDTO.getRentalId());
        rental.setName(rentalDTO.getName());
        rental.setInternetDomain(rentalDTO.getInternetDomain());
        rental.setContactAddress(rentalDTO.getContactAddress());
        return rental;
    }

    @Override
    public List<RentalDTO> toDtoList(List<Rental> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Rental> toEntityList(List<RentalDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
