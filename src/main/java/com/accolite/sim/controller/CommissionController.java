package com.accolite.sim.controller;

import com.accolite.sim.entity.Commission;
import com.accolite.sim.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commission")
public class CommissionController {

    @Autowired
    CommissionService commissionService;

    @GetMapping("/{vehicleType}")
    public List<Commission> getAllCommissionByVehicleType(@PathVariable String vehicleType) {
        return this.commissionService.getAllCommissionByVehicleType(vehicleType);
    }

    @GetMapping("/get/{commissionId}")
    public Commission getCommissionById(@PathVariable Integer commissionId) {
        return this.commissionService.getCommissionById(commissionId);
    }

    @PostMapping("/edit")
    public Commission getCommissionById(@RequestBody Commission newCommission) {
        return this.commissionService.updateCommission(newCommission);
    }

}
