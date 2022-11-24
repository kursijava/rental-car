package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.UnavailableStatus;
import com.roi.rentalcar.dtos.UnavailableStatusDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnavailableStatusMapper implements BaseMapper<UnavailableStatus, UnavailableStatusDTO>{
    @Override
    public UnavailableStatusDTO toDto(UnavailableStatus unavailableStatus) {
       if (unavailableStatus == null) return null;
       UnavailableStatusDTO unavailableStatusDTO = new UnavailableStatusDTO();
       unavailableStatusDTO.setStatusId(unavailableStatus.getStatusId());
       unavailableStatusDTO.setStatus(unavailableStatus.getStatus());
       unavailableStatusDTO.setDate(unavailableStatus.getDate());
       return unavailableStatusDTO;
    }

    @Override
    public UnavailableStatus toEntity(UnavailableStatusDTO unavailableStatusDTO) {
        if (unavailableStatusDTO == null) return null;
        UnavailableStatus unavailableStatus = new UnavailableStatus();
        unavailableStatus.setStatusId(unavailableStatusDTO.getStatusId());
        unavailableStatus.setStatus(unavailableStatusDTO.getStatus());
        unavailableStatus.setDate(unavailableStatusDTO.getDate());
        return unavailableStatus;
    }

    @Override
    public List<UnavailableStatusDTO> toDtoList(List<UnavailableStatus> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<UnavailableStatus> toEntityList(List<UnavailableStatusDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
