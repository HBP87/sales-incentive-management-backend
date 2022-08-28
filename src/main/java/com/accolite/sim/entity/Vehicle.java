package com.accolite.sim.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String type;
    private Integer cost;
    private Boolean isSoldOut = false;

    @JsonIgnore
    @OneToOne(mappedBy = "vehicle")
    private Sales sales;


    public Vehicle(String name, String type, int cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
}
