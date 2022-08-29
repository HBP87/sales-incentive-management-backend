package com.accolite.sim.dto;

import lombok.Data;

@Data
public class UpdateQuotaDTO {
    Integer quotaId;
    Integer newQuotaAmount;
}
