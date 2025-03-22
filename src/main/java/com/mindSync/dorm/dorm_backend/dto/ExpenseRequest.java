package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExpenseRequest {

    @NotBlank(message="not blank")
    private String purchasedBy;

    @NotBlank(message="not blank")
    private Double myContribution;

    @NotBlank(message="not blank")
    private Double totalAmount;

    @NotBlank(message="not blank")
    private Double contributionAmount;

    @NotBlank(message="not blank")
    private String teamMateName;
}
