package com.projectService.project.model.dto;

import com.projectService.project.model.ProjectModel;
import lombok.Getter;

@Getter
public class ProjectDTO {
    private String name;
    private String status;
    private Number size;

    public ProjectModel transformToProjectModel() {
        return new ProjectModel(name, status, size);
    }
}
