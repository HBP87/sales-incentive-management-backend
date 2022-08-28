package com.accolite.sim.repository;

import com.accolite.sim.entity.SalesTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTeamRepository extends JpaRepository<SalesTeam, Integer> {
}
