package com.accolite.sim.dto;

import com.accolite.sim.entity.SalesPerson;
import lombok.Data;

import java.util.List;

@Data
public class SalesTeamResponseDTO {
    private Integer id;
    private String name;
    private String location;
    private List<SalesPerson> salesPeople;
}
