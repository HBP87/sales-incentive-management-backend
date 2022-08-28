package com.accolite.sim.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_person_id", referencedColumnName = "id")
    private SalesPerson salesPerson;

    private Integer amount;
    private Date orderDate;

    public Sales(Vehicle vehicle, SalesPerson salesPerson, Integer amount, Date orderDate) {
        this.vehicle = vehicle;
        this.salesPerson = salesPerson;
        this.amount = amount;
        this.orderDate = orderDate;
    }
}
