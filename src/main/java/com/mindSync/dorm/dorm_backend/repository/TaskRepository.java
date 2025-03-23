package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.dto.RequestDto;
import com.mindSync.dorm.dorm_backend.dto.TaskRequest;
import com.mindSync.dorm.dorm_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT new com.mindSync.dorm.dorm_backend.dto.TaskRequest(t.id, t.taskTitle, t.description, t.assignTo, t.dueDate, t.category, t.priority) " +
            "FROM Task t WHERE t.user.id = :userId")
    List<TaskRequest> findTasksForUser(@Param("userId") Long userId);
}
