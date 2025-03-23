package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor  // Required for @Query result mapping
public class RequestDto {

    @NotBlank(message = "Request type cannot be blank")
    private String requestType;

    @NotBlank(message = "Request type cannot be blank")
    private String title;

    @NotBlank(message = "Request desc cannot be blank")
    private String description;

    private String status;

}
