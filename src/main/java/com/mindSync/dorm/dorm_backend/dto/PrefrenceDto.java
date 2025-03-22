package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PrefrenceDto {
    @NotBlank(message = "Night Owl preference is required")
    private String nightOwl;

    @NotBlank(message = "Socialize preference is required")
    private String socialize;

    @NotBlank(message = "Work from home preference is required")
    private String workFromHome;

    @NotBlank(message = "Vegetarian preference is required")
    private String vegetarian;

    @NotBlank(message = "Smoker preference is required")
    private String smoker;

    @NotBlank(message = "Okay with guest preference is required")
    private String okayWithGuest;

    @NotBlank(message = "Pet friendly preference is required")
    private String petFriendly;
}
