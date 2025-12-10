package com.example.pm.service;

import com.example.pm.dto.ProjectRequest;
import com.example.pm.model.Project;
import com.example.pm.model.User;
import com.example.pm.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project createProject(ProjectRequest request, User user) {
        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .user(user)
                .build();
        return projectRepository.save(project);
    }

    public List<Project> getMyProjects(User user) {
        return projectRepository.findByUserId(user.getId());
    }

    public Project getProject(Long id, User user) {
        return projectRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Project not found or access denied"));
    }

    public Project updateProject(Long id, ProjectRequest request, User user) {
        Project project = getProject(id, user);
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id, User user) {
        Project project = getProject(id, user);
        projectRepository.delete(project);
    }
}