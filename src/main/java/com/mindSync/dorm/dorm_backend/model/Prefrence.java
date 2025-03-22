package com.mindSync.dorm.dorm_backend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prefrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prefrenceId;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    private String nightOwl;
    private String socialize;
    private String workFromHome;
    private String vegetarian;
    private String smoker;
    private String okayWithGuest;
    private String petFriendly;
}
