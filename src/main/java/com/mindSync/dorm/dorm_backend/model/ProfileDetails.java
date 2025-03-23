package com.mindSync.dorm.dorm_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "userDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailsId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String name;
    private String phoneNo;
    private String branch;
    private String course;
    private String gender;
    private String dob;
    private String occupation;
}
