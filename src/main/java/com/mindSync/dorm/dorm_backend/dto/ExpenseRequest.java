package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExpenseRequest {

    @NotBlank(message="not blank")
    private String description;

    @NotNull(message="not blank")
    private double myContribution;

    @NotNull(message="not blank")
    private double totalAmount;



}
