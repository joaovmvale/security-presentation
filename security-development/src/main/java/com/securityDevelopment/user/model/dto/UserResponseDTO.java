package com.securityDevelopment.user.model.dto;

import com.securityDevelopment.user.model.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String lastName;

    public static UserResponseDTO transformToUserResponseDTO(UserModel user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getLastName());
    }
}
