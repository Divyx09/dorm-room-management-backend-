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
@Table(name="expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseId;


    private String purchasedBy;

    private double myContribution;

    private double myRemaining;

    private double totalAmount;

    private Double teammateContributionAmount;

    private String teamMateName;

    private String roomNumber;

    private String description;

    private LocalDateTime createdAt;

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    private LocalDateTime updatedAt;

    private String updatedBy;

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key referencing User entity
    private User user;


}
