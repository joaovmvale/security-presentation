package com.securityDevelopment.user.model.dto;

import com.securityDevelopment.user.model.UserModel;
import lombok.Getter;

@Getter
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;

    public UserModel transformToUserModel() {
        return new UserModel(username, password, email, name, lastName);
    }
}
