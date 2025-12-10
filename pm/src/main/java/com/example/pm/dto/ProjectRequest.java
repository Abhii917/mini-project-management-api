package com.example.pm.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data public class ProjectRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
}