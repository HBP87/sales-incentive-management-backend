package com.accolite.sim;

import com.accolite.sim.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {

    @Autowired
    private CommissionRepository commissionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("=====================");
        System.out.println("Server is up and running.");
        System.out.println("=====================");
    }
}
