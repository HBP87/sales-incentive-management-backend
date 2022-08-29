package com.accolite.sim.controller;

import com.accolite.sim.entity.Quota;
import com.accolite.sim.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quota")
public class QuotaController {

    @Autowired
    QuotaService quotaService;

    @GetMapping
    public List<Quota> getAllQuota() {
        return this.quotaService.getAllQuota();
    }

    @GetMapping("/{location}")
    public Quota getQuotaByLocation(@PathVariable String location) {
        return this.quotaService.getQuotaByLocation(location);
    }
}
