package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails,Long> {
    @Query("SELECT p FROM ProfileDetails p WHERE p.user.id = :userId")
    Optional<ProfileDetails> findByUserId(@Param("userId") Long userId);
}
