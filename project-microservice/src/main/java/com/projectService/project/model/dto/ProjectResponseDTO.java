package com.projectService.project.model.dto;

import com.projectService.project.model.ProjectModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectResponseDTO {
    private UUID id;
    private String name;
    private String status;
    private Number size;

    public static ProjectResponseDTO transformToProjectResponseDTO(ProjectModel project) {
        return new ProjectResponseDTO(project.getId(), project.getName(), project.getStatus(), project.getSize());
    }
}
