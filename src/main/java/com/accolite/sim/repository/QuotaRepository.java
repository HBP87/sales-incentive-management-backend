package com.accolite.sim.repository;

import com.accolite.sim.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Integer> {

    Quota findByLocation(String location);
}
