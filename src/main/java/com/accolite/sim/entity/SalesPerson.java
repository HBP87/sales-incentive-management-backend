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
public class SalesPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String designation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_team_id", referencedColumnName = "id")
    private SalesTeam salesTeam;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "person_below_id")
    private SalesPerson personBelow;

    @OneToOne(mappedBy = "personBelow")
    private SalesPerson personAbove;


    @JsonIgnore
    @OneToMany(mappedBy = "salesPerson")
    private List<Sales> sales;

    private Long commissionAmount;

    public SalesPerson(String name, String username, String password,
                       String designation, SalesTeam salesTeam, SalesPerson personBelow,
                       SalesPerson personAbove, Long commissionAmount) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.designation = designation;
        this.salesTeam = salesTeam;
        this.personBelow = personBelow;
        this.personAbove = personAbove;
        this.commissionAmount = commissionAmount;
    }

    public void addSale(Sales sale) {
        if (sales == null) {
            sales = new ArrayList<>();
        }
        sales.add(sale);
    }
}
