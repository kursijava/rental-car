package com.roi.rentalcar.mappers;

import com.roi.rentalcar.database.entities.Refund;
import com.roi.rentalcar.dtos.RefundDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RefundMapper implements BaseMapper<Refund, RefundDTO>{
    @Override
    public RefundDTO toDto(Refund refund) {
        if (refund == null) return null;
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setRefundId(refund.getRefundId());
        refundDTO.setRefund(refund.getRefund());
        refundDTO.setComment(refund.getComment());
        refundDTO.setSurcharge(refund.getSurcharge());
        refundDTO.setReturnDate(refund.getReturnDate());
        return refundDTO;
    }

    @Override
    public Refund toEntity(RefundDTO refundDTO) {
        if (refundDTO == null) return null;
        Refund refund = new Refund();
        refund.setRefundId(refundDTO.getRefundId());
        refund.setRefund(refundDTO.getRefund());
        refund.setComment(refundDTO.getComment());
        refund.setSurcharge(refundDTO.getSurcharge());
        refund.setReturnDate(refundDTO.getReturnDate());
        return refund;
    }

    @Override
    public List<RefundDTO> toDtoList(List<Refund> e) {
        if (e == null) return null;
        return e.stream().map(this::toDto).toList();
    }

    @Override
    public List<Refund> toEntityList(List<RefundDTO> d) {
        if (d == null) return null;
        return d.stream().map(this::toEntity).toList();
    }
}
