package com.roi.rentalcar.services;

import java.util.List;

public interface CrudService<T, I> {
    T getById(I id);
    List<T> getAll();
    T create(T dto);
    T update(T dto);
    String deleteById(I id);
}
