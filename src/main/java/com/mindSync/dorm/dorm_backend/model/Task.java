package com.mindSync.dorm.dorm_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_type")
    private String taskTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "assignTo")
    private String assignTo;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "category")
    private String category;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id") // Foreign key referencing User entity
    private User user;
}