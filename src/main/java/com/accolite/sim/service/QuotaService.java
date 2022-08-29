package com.accolite.sim.service;

import com.accolite.sim.entity.Quota;
import com.accolite.sim.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuotaService {

    @Autowired
    QuotaRepository quotaRepository;

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
}
