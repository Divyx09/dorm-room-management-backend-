package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RoomRequest {

    @NotBlank(message = "Room number cannot be blank")
    private String roomNumber;

    @Min(value = 1, message = "Floor number must be at least 1")
    private int floorNo;

    @NotNull(message = "Occupied status cannot be null")
    private String isOccupied;

    @Min(value = 1, message = "Max capacity must be at least 1")
    private int maxCapacity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Getters and Setters
}
