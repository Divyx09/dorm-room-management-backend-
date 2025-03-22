package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ServiceRequest {
    @NotBlank(message="not blank")
    public String serviceName;

    @NotBlank(message="not blank")
    public String description;
    @NotBlank(message="not blank")
    private String status;
}
