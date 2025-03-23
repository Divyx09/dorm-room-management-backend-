package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.TaskRequest;
import com.mindSync.dorm.dorm_backend.model.Task;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.TaskRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    // Create or Save a Task from DTO
    public Task createTask(TaskRequest taskRequest) {

        Task task = mapToTaskWithBuilder(taskRequest, LocalDateTime.now(), LocalDateTime.now());
        return taskRepository.save(task);
    }

    // Retrieve all Tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Retrieve a Task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Update a Task using DTO and Builder pattern for mapping updated fields
    public Task updateTask(Long id, TaskRequest taskRequest) {
        return taskRepository.findById(id).map(existingTask -> {
            Task updatedTask = Task.builder()
                    .id(existingTask.getId())  // Keep the same ID
                    .taskTitle(taskRequest.getTaskTitle())
                    .description(taskRequest.getDescription())
                    .assignTo(taskRequest.getAssignTo())
                    .dueDate(taskRequest.getDueDate())
                    .category(taskRequest.getCategory())
                    .priority(taskRequest.getPriority())
                    .createdAt(existingTask.getCreatedAt())  // Preserve createdAt timestamp
                    .updatedAt(LocalDateTime.now())         // Update updatedAt timestamp
                    .build();
            return taskRepository.save(updatedTask);
        }).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    // Delete a Task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Utility: Map TaskRequest DTO to Task Entity (with timestamps passed as parameters)
    private Task mapToTaskWithBuilder(TaskRequest taskRequest, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("uaer not found"));

        return Task.builder()
                .taskTitle(taskRequest.getTaskTitle())
                .description(taskRequest.getDescription())
                .assignTo(taskRequest.getAssignTo())
                .dueDate(taskRequest.getDueDate())
                .category(taskRequest.getCategory())
                .priority(taskRequest.getPriority())
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .user(user)
                .build();
    }
}
