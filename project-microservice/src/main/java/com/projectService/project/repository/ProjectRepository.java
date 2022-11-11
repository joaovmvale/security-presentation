package com.projectService.project.repository;

import com.projectService.project.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
}
