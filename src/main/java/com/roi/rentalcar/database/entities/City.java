package com.roi.rentalcar.database.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class City {
    @Id
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Branch> branches;
}
