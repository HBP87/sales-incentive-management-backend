package com.accolite.sim.repository;

import com.accolite.sim.entity.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson, Integer> {

    SalesPerson findByUsername(String username);

}
