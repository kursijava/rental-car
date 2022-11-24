package com.roi.rentalcar.database.repositories;

import com.roi.rentalcar.database.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
    @Query(value = "select branch from Branch branch where branch.name = :name")
    Branch getByName(@Param("name") String branchName);
    @Query("select case when (count(b) > 0 ) then true else false end from Branch b where b.name = :name")
    Boolean existsBranchByName(@Param("name") String name);
}
