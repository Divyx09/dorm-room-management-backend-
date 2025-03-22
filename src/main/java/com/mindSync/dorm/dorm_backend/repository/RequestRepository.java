package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Long> {
}
