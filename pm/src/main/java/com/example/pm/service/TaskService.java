package com.example.pm.service;

import com.example.pm.dto.TaskRequest;
import com.example.pm.model.*;
import com.example.pm.model.enums.TaskPriority;
import com.example.pm.model.enums.TaskStatus;
import com.example.pm.repository.ProjectRepository;
import com.example.pm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public Task createTask(Long projectId, TaskRequest request, User user) {
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : TaskStatus.PENDING)
                .priority(request.getPriority() != null ? request.getPriority() : TaskPriority.LOW)
                .dueDate(request.getDueDate())
                .project(project)
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getTasks(Long projectId, User user) {
        // Simple verification that user owns project
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return project.getTasks();
    }

    public Task updateTask(Long taskId, TaskRequest request, User user) {
        Task task = taskRepository.findTaskByIdAndUserId(taskId, user.getId());
        if(task == null) throw new RuntimeException("Task not found");

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if(request.getStatus() != null) task.setStatus(request.getStatus());
        if(request.getPriority() != null) task.setPriority(request.getPriority());
        if(request.getDueDate() != null) task.setDueDate(request.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId, User user) {
        Task task = taskRepository.findTaskByIdAndUserId(taskId, user.getId());
        if(task == null) throw new RuntimeException("Task not found");
        taskRepository.delete(task);
    }

    public List<Task> searchTasks(String keyword, String sortBy, User user) {
        List<Task> tasks = taskRepository.searchTasks(user.getId(), keyword);

        if ("dueDate".equalsIgnoreCase(sortBy)) {
            tasks.sort(Comparator.comparing(Task::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())));
        } else if ("priority".equalsIgnoreCase(sortBy)) {
            tasks.sort(Comparator.comparing(Task::getPriority));
        }
        return tasks;
    }
}