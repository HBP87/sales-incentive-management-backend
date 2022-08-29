package com.accolite.sim.service;

import com.accolite.sim.entity.Commission;
import com.accolite.sim.entity.Sales;
import com.accolite.sim.entity.SalesPerson;
import com.accolite.sim.entity.Vehicle;
import com.accolite.sim.repository.CommissionRepository;
import com.accolite.sim.repository.SalesPersonRepository;
import com.accolite.sim.repository.SalesRepository;
import com.accolite.sim.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    SalesPersonRepository salesPersonRepository;

    @Autowired
    CommissionRepository commissionRepository;

    public Sales getSaleById(Integer id) {
        Sales sale;
        try {
            sale = this.salesRepository.findById(id).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Sale with id = " + id + " not found.");
        }
        return sale;
    }

    public List<Sales> getAllSalesByUsername(String username) {
        SalesPerson salesPerson = this.salesPersonRepository.findByUsername(username);
        return this.salesRepository.findAllBySalesPerson(salesPerson);
    }


    public Sales makeSale(Integer vehicleId, String username, Date... varArgs) {
        Date givenDate = null;
        try {
            givenDate = varArgs[0];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        Vehicle vehicle;
        SalesPerson salesPerson;
        try {
            vehicle = this.vehicleRepository.findById(vehicleId).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Vehicle with id: " + vehicleId + " not found");
        }
        try {
            salesPerson = this.salesPersonRepository.findByUsername(username);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Sales Person with username: " + username + " not found");
        }
        if (vehicle.getIsSoldOut()) {
            throw new EntityExistsException("Vehicle sold out");
        }
        vehicle.setIsSoldOut(true);
        this.vehicleRepository.save(vehicle);

        //Commission Starts
        List<Commission> commissions = this.commissionRepository.findAllByVehicleType(vehicle.getType());
        Integer vehicleCost = vehicle.getCost();
        Integer percentage = 0;
        for (Commission commission : commissions) {
            Integer startAmount = commission.getStartAmount();
            Integer endAmount = commission.getEndAmount();
            if (vehicleCost >= startAmount && vehicleCost < endAmount) {
                percentage = commission.getInterest();
                break;
            }
        }
        Long directCommission = ((long) vehicleCost * percentage / 100);
        salesPerson.setCommissionAmount(salesPerson.getCommissionAmount() + directCommission);

        SalesPerson level1 = salesPerson.getPersonAbove();
        SalesPerson level2 = level1.getPersonAbove();
        SalesPerson level3 = level2.getPersonAbove();

        level1.setCommissionAmount(level1.getCommissionAmount() + (directCommission / 10));
        level2.setCommissionAmount(level2.getCommissionAmount() + (directCommission / 20));
        level3.setCommissionAmount(level3.getCommissionAmount() + (directCommission / 50));

        this.salesPersonRepository.save(salesPerson);
        this.salesPersonRepository.save(level1);
        this.salesPersonRepository.save(level2);
        this.salesPersonRepository.save(level3);
        //Commission End

        Sales sales;
        if (givenDate == null) {
            sales = new Sales(vehicle, salesPerson, vehicle.getCost(), new Date());
        } else {
            sales = new Sales(vehicle, salesPerson, vehicle.getCost(), givenDate);
        }
        return this.salesRepository.save(sales);
    }
}
