package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Services,Long> {
//    @Query("SELECT COUNT(p) > 0 FROM services p WHERE p.name = :name AND p.user.id = :userId")
//    boolean existsByServiceAndUserId(@Param("name") String name, @Param("sellerId") Long userId);

}
