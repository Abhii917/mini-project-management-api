package com.example.pm.controller;

import com.example.pm.dto.ProjectRequest;
import com.example.pm.model.Project;
import com.example.pm.model.User;
import com.example.pm.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Valid ProjectRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.createProject(request, user));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.getMyProjects(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.getProject(id, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody ProjectRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.updateProject(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        projectService.deleteProject(id, user);
        return ResponseEntity.noContent().build();
    }
}