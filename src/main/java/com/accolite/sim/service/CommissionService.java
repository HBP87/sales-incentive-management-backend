package com.accolite.sim.service;

import com.accolite.sim.entity.Commission;
import com.accolite.sim.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommissionService {
    @Autowired
    CommissionRepository commissionRepository;

    public List<Commission> getAllCommissionByVehicleType(String vehicleType) {
        List<Commission> commissions;

        try {
            commissions = this.commissionRepository.findAllByVehicleType(vehicleType);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Commission for vehicle type " + vehicleType + " not found");
        }
        return commissions;
    }

    public Commission getCommissionById(Integer id) {
        Commission commission;
        try {
            commission = this.commissionRepository.findById(id).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Commission with id: " + id + " not present");
        }
        return commission;
    }

    public Commission updateCommission(Commission newCommission) {
        Integer id = newCommission.getId();
        Commission commission = this.getCommissionById(id);
        commission.setStartAmount(newCommission.getStartAmount());
        commission.setEndAmount(newCommission.getEndAmount());
        commission.setInterest(newCommission.getInterest());
        return this.commissionRepository.save(commission);
    }
}
