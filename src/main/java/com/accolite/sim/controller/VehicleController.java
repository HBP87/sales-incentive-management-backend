package com.accolite.sim.controller;

import com.accolite.sim.dto.VehicleDTO;
import com.accolite.sim.entity.Vehicle;
import com.accolite.sim.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;


    @PostMapping
    public Vehicle createVehicle(@RequestBody VehicleDTO vehicle) {
        String name = vehicle.getName();
        String type = vehicle.getType();
        Integer cost = vehicle.getCost();

        List<Vehicle> allVehicles = this.vehicleService.getAllVehicles();
        for (Vehicle eachVehicle : allVehicles) {
            if (eachVehicle.getName().equals(name) && eachVehicle.getType().equals(type)) {
                throw new EntityExistsException("Vehicle with same name & type already exists.");
            }
        }

        Vehicle vehicle1 = new Vehicle(name, type, cost);
        this.vehicleService.createVehicle(vehicle1);
        return vehicle1;
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable("id") Integer id) {
        return this.vehicleService.getVehicleById(id);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles(){
        return this.vehicleService.findAllVehicles();
    }
}
