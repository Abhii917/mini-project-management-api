package com.example.pm.repository;

import com.example.pm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Check if task belongs to a project owned by the user
    @Query("SELECT t FROM Task t WHERE t.id = :taskId AND t.project.user.id = :userId")
    Task findTaskByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);

    // Search tasks across all projects for a specific user
    @Query("SELECT t FROM Task t WHERE t.project.user.id = :userId AND " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Task> searchTasks(@Param("userId") Long userId, @Param("keyword") String keyword);
}