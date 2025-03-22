package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.Prefrence;
import com.mindSync.dorm.dorm_backend.model.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrefrenceRepository extends JpaRepository<Prefrence,Long> {
    @Query("SELECT p FROM Prefrence p WHERE p.user.id = :userId")
    Prefrence findByUserId(@Param("userId") Long userId);

}
