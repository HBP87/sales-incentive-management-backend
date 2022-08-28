package com.accolite.sim.service;

import com.accolite.sim.entity.SalesPerson;
import com.accolite.sim.repository.SalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SalesPersonService {

    @Autowired
    SalesPersonRepository salesPersonRepository;

    public SalesPerson getSalesPersonById(Integer id) {
        SalesPerson salesPerson;
        try {
            salesPerson = this.salesPersonRepository.findById(id).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Sales Person with id = " + id + " is not present");
        }
        return salesPerson;
    }
}
