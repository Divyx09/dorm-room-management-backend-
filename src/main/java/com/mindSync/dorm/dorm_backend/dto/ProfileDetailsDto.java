package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProfileDetailsDto {
    @NotBlank(message = "Phone number is required")
//    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNo;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "Course is required")
    private String course;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Date of Birth is required")
    private String dob; // Consider using LocalDate if handling date properly

    @NotBlank(message = "Occupation is required")
    private String occupation;
}
