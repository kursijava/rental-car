package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.entities.Branch;
import com.roi.rentalcar.database.entities.Rental;
import com.roi.rentalcar.database.entities.Reservation;
import com.roi.rentalcar.database.entities.Revenue;
import com.roi.rentalcar.database.repositories.RentalRepo;
import com.roi.rentalcar.database.repositories.ReservationRepo;
import com.roi.rentalcar.database.repositories.RevenueRepo;
import com.roi.rentalcar.dtos.RevenueDTO;
import com.roi.rentalcar.mappers.RevenueMapper;
import com.roi.rentalcar.services.RevenueService;
import com.roi.rentalcar.static_data.StaticMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    private RevenueRepo revenueRepo;
    @Autowired
    private RevenueMapper revenueMapper;
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private RentalRepo rentalRepo;
    @Override
    public RevenueDTO getById(Long id) {
        return null;
    }

    @Override
    public List<RevenueDTO> getAll() {
        return null;
    }

    @Override
    public RevenueDTO create(RevenueDTO dto) {
        return null;
    }

    @Override
    public RevenueDTO update(RevenueDTO dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }

    @Override
    public RevenueDTO getRevenueByRental(Long rentalId) {
        Rental rental = rentalRepo.findById(rentalId).orElseThrow(()->
           new RuntimeException(StaticMessages.setIdNotFound(Rental.class, rentalId)));
        Integer month = LocalDate.now().getMonth().getValue();
        List<Branch> branches = rental.getBranches();
        Double total = 0.0;
        for (Branch branch:branches
             ) {
            List<Reservation> reservations = reservationRepo.getAllBranchAndMonth(branch, month);
            for (Reservation reservation:reservations
                 ) {
                total = total + reservation.getAmount();
            }
        }
        Revenue revenue = new Revenue();
        revenue.setRental(rental);
        revenue.setMonth(LocalDate.now().getMonth().name());
        revenue.setAmount(total);
        revenue = revenueRepo.save(revenue);
        return revenueMapper.toDto(revenue);
    }
}
