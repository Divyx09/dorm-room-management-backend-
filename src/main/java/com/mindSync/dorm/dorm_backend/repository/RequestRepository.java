package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.dto.RequestDto;
import com.mindSync.dorm.dorm_backend.model.Request;
import com.mindSync.dorm.dorm_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request,Long> {

    Optional<Request> findByTitleAndUser(String title, User user);
    @Query("SELECT new com.mindSync.dorm.dorm_backend.dto.RequestDto(" +
            "r.requestType, r.title, r.description, r.status) " + // Remove AS aliases
            "FROM Request r WHERE r.user.id = :userId")
    List<RequestDto> findRequestsByUserId(@Param("userId") Long userId);
}
