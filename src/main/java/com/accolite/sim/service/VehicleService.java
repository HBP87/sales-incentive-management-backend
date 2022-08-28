package com.accolite.sim.service;

import com.accolite.sim.entity.Vehicle;
import com.accolite.sim.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
    }

    public void createVehicle(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(Integer id) {
        Vehicle vehicle;
        try {
            vehicle = this.vehicleRepository.findById(id).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Vehicle with id " + id + " not found.");
        }
        return vehicle;
    }

    public List<Vehicle> findAllVehicles() {
        return this.vehicleRepository.findAll();
    }
}
