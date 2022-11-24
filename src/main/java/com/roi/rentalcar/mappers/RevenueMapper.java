package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Revenue;
import com.roi.rentalcar.dtos.RevenueDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RevenueMapper implements BaseMapper<Revenue, RevenueDTO>{
    @Override
    public RevenueDTO toDto(Revenue revenue) {
        if (revenue == null) return null;
        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setRevenueId(revenue.getRevenueId());
        revenueDTO.setAmount(revenue.getAmount());
        revenueDTO.setMonth(revenue.getMonth());
        return revenueDTO;
    }

    @Override
    public Revenue toEntity(RevenueDTO revenueDTO) {
        if (revenueDTO == null) return null;
        Revenue revenue = new Revenue();
        revenue.setRevenueId(revenueDTO.getRevenueId());
        revenue.setAmount(revenueDTO.getAmount());
        revenue.setMonth(revenueDTO.getMonth());
        return revenue;    }

    @Override
    public List<RevenueDTO> toDtoList(List<Revenue> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Revenue> toEntityList(List<RevenueDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
