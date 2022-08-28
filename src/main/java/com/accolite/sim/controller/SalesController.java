package com.accolite.sim.controller;


import com.accolite.sim.dto.MakeSaleDTO;
import com.accolite.sim.entity.Sales;
import com.accolite.sim.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/{username}")
    public List<Sales> getAllSalesByUsername(@PathVariable String username) {
        return this.salesService.getAllSalesByUsername(username);
    }


    @PostMapping("/new")
    public Sales makeSale(@RequestBody MakeSaleDTO makeSaleDTO) {
        Integer vehicleId = makeSaleDTO.getVehicleId();
        String username = makeSaleDTO.getUsername();
        return this.salesService.makeSale(vehicleId, username);
    }
}
