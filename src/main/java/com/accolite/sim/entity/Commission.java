package com.accolite.sim.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String vehicleType;
    private Integer startAmount;
    private Integer endAmount;
    private Integer interest;

    public Commission(String vehicleType, Integer startAmount, Integer endAmount, Integer interest) {
        this.vehicleType = vehicleType;
        this.startAmount = startAmount;
        this.endAmount = endAmount;
        this.interest = interest;
    }
}
