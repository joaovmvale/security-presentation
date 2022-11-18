package com.securityDevelopment.userProject.model.dto;

import com.securityDevelopment.userProject.model.UserProjectModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProjectResponseDTO {
    private UUID id;
    private UUID userId;
    private UUID projectId;

    public static UserProjectResponseDTO transformToUserProjectResponseDTO(UserProjectModel userProjectModel) {
        return new UserProjectResponseDTO(userProjectModel.getId(), userProjectModel.getUserId(), userProjectModel.getProjectId());
    }
}
