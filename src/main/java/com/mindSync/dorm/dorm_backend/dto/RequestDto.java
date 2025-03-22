package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestDto {

    @NotBlank(message = "Request type cannot be blank")
    private String requestType;

    @NotBlank(message = "Request desc cannot be blank")
    private String description;

    private String status;

}
