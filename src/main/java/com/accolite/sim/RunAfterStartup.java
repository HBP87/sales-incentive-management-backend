package com.accolite.sim;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {


    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("==========");
        System.out.println("Server is up and running");
        System.out.println("===========");
    }
}
