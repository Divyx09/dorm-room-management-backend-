package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskRequest {

    private Long id;  // ✅ Changed to Long (matches DB type)

    @NotBlank(message = "Task title cannot be blank")
    private String taskTitle;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private String assignTo;
    private String dueDate;
    private String category;
    private String priority;

    // ✅ Corrected constructor order and id type
    public TaskRequest(Long id, String taskTitle, String description, String assignTo, String dueDate, String category, String priority) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.description = description;
        this.assignTo = assignTo;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
    }
}
