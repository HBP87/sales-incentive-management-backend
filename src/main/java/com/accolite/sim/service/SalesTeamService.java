package com.accolite.sim.service;

import com.accolite.sim.entity.SalesPerson;
import com.accolite.sim.entity.SalesTeam;
import com.accolite.sim.repository.SalesPersonRepository;
import com.accolite.sim.repository.SalesTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SalesTeamService {
    @Autowired
    SalesTeamRepository salesTeamRepository;

    @Autowired
    SalesPersonRepository salesPersonRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public SalesTeam getSalesTeamById(Integer id) {
        SalesTeam salesTeam;
        try {
            salesTeam = this.salesTeamRepository.findById(id).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Sales Team with id " + id + " not found.");
        }
        return salesTeam;
    }

    public void createSalesTeam(SalesTeam team) {
        List<SalesTeam> teams = this.getAllSalesTeam();
        for (SalesTeam eachTeam : teams) {
            if (eachTeam.getName().equals(team.getName()) && eachTeam.getLocation().equals(team.getLocation())) {
                throw new EntityExistsException("Team with same name & location already exists.");
            }
        }
        this.salesTeamRepository.save(team);
    }

    public List<SalesTeam> getAllSalesTeam() {
        return this.salesTeamRepository.findAll();
    }

    public SalesTeam addSalesPersonToTeam(SalesPerson salesPerson, Integer id) {
        List<SalesPerson> personList = this.salesPersonRepository.findAll();
        for (SalesPerson person : personList) {
            if (person.getUsername().equals(salesPerson.getUsername())) {
                throw new EntityExistsException("Sales Person with username " + person.getUsername() + " already exists.");
            }
        }
        SalesTeam team = this.getSalesTeamById(id);
        salesPerson.setSalesTeam(team);
        salesPerson.setPassword(encoder.encode(salesPerson.getPassword()));
        this.salesPersonRepository.saveAndFlush(salesPerson);
        return team;
    }
}
