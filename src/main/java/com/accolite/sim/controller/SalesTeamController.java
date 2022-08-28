package com.accolite.sim.controller;

import com.accolite.sim.dto.SalesPersonDTO;
import com.accolite.sim.dto.SalesTeamDTO;
import com.accolite.sim.dto.SalesTeamResponseDTO;
import com.accolite.sim.entity.SalesPerson;
import com.accolite.sim.entity.SalesTeam;
import com.accolite.sim.service.SalesTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sales-team")
public class SalesTeamController {

    @Autowired
    SalesTeamService salesTeamService;

    @PostMapping
    public SalesTeam createSalesTeam(@RequestBody SalesTeamDTO newTeam) {
        String name = newTeam.getName();
        String location = newTeam.getLocation();
        SalesTeam team = new SalesTeam(name, location);
        this.salesTeamService.createSalesTeam(team);
        return team;
    }


    @PostMapping("/add-member")
    public SalesTeam addSalesPersonToAnExistingSalesTeam(@RequestBody SalesPersonDTO salesPerson) {
        String name = salesPerson.getName();
        String username = salesPerson.getUsername();
        String password = salesPerson.getPassword();

        String designation = salesPerson.getDesignation();
        Integer salesTeamId = salesPerson.getSalesTeamId();

        SalesPerson person = new SalesPerson(name, username, password, designation,
                null, null, null, 0L);
        return this.salesTeamService.addSalesPersonToTeam(person, salesTeamId);
    }

    @GetMapping
    public List<SalesTeam> getAllSalesTeams() {
        return this.salesTeamService.getAllSalesTeam();
    }

    @GetMapping("/{id}")
    public SalesTeamResponseDTO getSalesTeamById(@PathVariable Integer id) {
        SalesTeam team = this.salesTeamService.getSalesTeamById(id);
        SalesTeamResponseDTO responseDTO = new SalesTeamResponseDTO();
        responseDTO.setId(team.getId());
        responseDTO.setName(team.getName());
        responseDTO.setLocation(team.getLocation());
        responseDTO.setSalesPeople(team.getSalesPeople());
        return responseDTO;
    }

}
