package com.example.pm.dto;
import com.example.pm.model.enums.TaskPriority;
import com.example.pm.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data public class TaskRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private TaskStatus status; // Optional, defaults to PENDING
    private TaskPriority priority; // Optional, defaults to LOW
    private LocalDateTime dueDate;
}