package com.accolite.sim.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class SalesTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesTeam")
    private List<SalesPerson> salesPeople;

    public SalesTeam(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addSalesPersonToSalesTeam(SalesPerson salesPerson) {
        if (salesPeople == null) {
            this.salesPeople = new ArrayList<>();
        }
        this.salesPeople.add(salesPerson);
    }
}
