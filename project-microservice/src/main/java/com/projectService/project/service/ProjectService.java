package com.projectService.project.service;

import com.projectService.project.model.ProjectModel;
import com.projectService.project.repository.ProjectRepository;
import com.projectService.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectModel create(ProjectModel project) throws CustomException {
        if (project.getName() == null || project.getName().equals("") || project.getName().length() > 300) {
            throw new CustomException("ERR001", "Project name is wrong.");
        }
        return projectRepository.save(project);
    }

    public List<ProjectModel> listAll() {
        return projectRepository.findAll();
    }

    public ProjectModel getById(UUID id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }

}
