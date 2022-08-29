package com.accolite.sim.service;

import com.accolite.sim.entity.Quota;
import com.accolite.sim.entity.SalesPerson;
import com.accolite.sim.repository.QuotaRepository;
import com.accolite.sim.repository.SalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuotaService {

    @Autowired
    QuotaRepository quotaRepository;

    @Autowired
    SalesPersonRepository personRepository;

    public Quota getQuotaByLocation(String location) {
        Quota quota;
        try {
            quota = this.quotaRepository.findByLocation(location);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Quota with location " + location + " not found");
        }
        return quota;
    }

    public List<Quota> getAllQuota() {
        return this.quotaRepository.findAll();
    }

    public Quota editQuota(Integer quotaId, Integer newQuotaAmount) {
        Quota quota;
        try {
            quota = this.quotaRepository.findById(quotaId).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Quota with id: " + quotaId + " not found");
        }
        quota.setQuotaAmount(newQuotaAmount);
        return this.quotaRepository.save(quota);
    }

    public Quota getQuotaByUsername(String username) {
        SalesPerson salesPerson;
        try {
            salesPerson = this.personRepository.findByUsername(username);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Username: " + username + " not found");
        }
        String location = salesPerson.getSalesTeam().getLocation();
        return this.getQuotaByLocation(location);
    }
}
