package com.mindSync.dorm.dorm_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roomNumber;
    private int floorNo;
    private String isOccupied;
    private int maxCapacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}