package com.securityDevelopment.userProject.service;

import com.securityDevelopment.projectService.ProjectService;
import com.securityDevelopment.userProject.model.UserProjectModel;
import com.securityDevelopment.userProject.repository.UserProjectRepository;
import com.securityDevelopment.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserProjectService {
    private final UserProjectRepository userProjectRepository;
    private final ProjectService projectService;

    @Autowired
    public UserProjectService(UserProjectRepository userProjectRepository, ProjectService projectService) {
        this.userProjectRepository = userProjectRepository;
        this.projectService = projectService;
    }

    public UserProjectModel create(UserProjectModel userProjectModel) throws CustomException {
        return userProjectRepository.save(userProjectModel);
    }

    public UserProjectModel associateUserToProject(UserProjectModel userProjectModel) throws CustomException {
        try {
            this.projectService.findById(userProjectModel.getProjectId());
            return userProjectRepository.save(userProjectModel);
        } catch (Exception e) {
            throw new CustomException("ERROR", "Project not found");
        }
    }

    public List<UserProjectModel> listAll() {
        return userProjectRepository.findAll();
    }

    public UserProjectModel getById(UUID id) {
        return userProjectRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        userProjectRepository.deleteById(id);
    }

}


