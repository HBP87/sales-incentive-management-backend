package com.accolite.sim.service;

import com.accolite.sim.entity.Quota;
import com.accolite.sim.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class QuotaService {

    @Autowired
    QuotaRepository quotaRepository;

    Quota getQuotaByLocation(String location) {
        Quota quota;
        try {
            quota = this.quotaRepository.findByLocation(location);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Quota with location " + location + " not found");
        }
        return quota;
    }
}
