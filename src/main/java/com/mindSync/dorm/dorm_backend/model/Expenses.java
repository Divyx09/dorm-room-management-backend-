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
    private long id;


    private String purchasedBy;

    private Double myContribution;

    private Double totalAmount;

    private Double contributionAmount;

    private String teamMateName;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key referencing User entity
    private User user;

}
