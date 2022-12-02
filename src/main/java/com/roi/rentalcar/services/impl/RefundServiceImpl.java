package com.roi.rentalcar.services.impl;

import com.roi.rentalcar.database.repositories.RefundRepo;
import com.roi.rentalcar.dtos.RefundDTO;
import com.roi.rentalcar.services.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundRepo refundRepo;
    @Override
    public RefundDTO getById(Long id) {
        return null;
    }

    @Override
    public List<RefundDTO> getAll() {
        return null;
    }

    @Override
    public RefundDTO create(RefundDTO dto) {
        return null;
    }

    @Override
    public RefundDTO update(RefundDTO dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }
}
