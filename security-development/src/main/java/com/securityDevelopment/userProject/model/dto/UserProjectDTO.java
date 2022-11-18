package com.securityDevelopment.userProject.model.dto;

import com.securityDevelopment.userProject.model.UserProjectModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserProjectDTO {
    private UUID userId;
    private UUID projectId;

    public UserProjectModel transformToUserProjectModel() {
        return new UserProjectModel(userId, projectId);
    }
}
