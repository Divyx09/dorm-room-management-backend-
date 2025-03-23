package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TaskRequest {

    @NotBlank(message = "Request type cannot be blank")
    private String taskTitle;

    @NotBlank(message = "Request desc cannot be blank")
    private String description;

    private String assignTo;
    private String dueDate;
    private String category;
    private String priority;

}


