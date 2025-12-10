package com.example.pm.controller;

import com.example.pm.dto.TaskRequest;
import com.example.pm.model.Task;
import com.example.pm.model.User;
import com.example.pm.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // Create Task under a project
    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<Task> create(@PathVariable Long projectId, @RequestBody @Valid TaskRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.createTask(projectId, request, user));
    }

    // Get Tasks for a project
    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<Task>> getAllInProject(@PathVariable Long projectId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getTasks(projectId, user));
    }

    // Update Task (Global ID, but checks ownership)
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Task> update(@PathVariable Long taskId, @RequestBody TaskRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.updateTask(taskId, request, user));
    }

    // Delete Task
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long taskId, @AuthenticationPrincipal User user) {
        taskService.deleteTask(taskId, user);
        return ResponseEntity.noContent().build();
    }

    // Search and Filter across all projects
    @GetMapping("/tasks/search")
    public ResponseEntity<List<Task>> search(
            @RequestParam String keyword,
            @RequestParam(required = false) String sortBy,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.searchTasks(keyword, sortBy, user));
    }
}