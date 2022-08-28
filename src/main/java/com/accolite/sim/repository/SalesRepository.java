package com.accolite.sim.repository;

import com.accolite.sim.entity.Sales;
import com.accolite.sim.entity.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
    List<Sales> findAllBySalesPerson(SalesPerson salesPerson);
}
