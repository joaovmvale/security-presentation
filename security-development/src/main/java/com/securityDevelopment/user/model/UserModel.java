package com.securityDevelopment.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotBlank(message = "Field username is mandatory")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Field password is mandatory")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Field email is mandatory")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Field name is mandatory")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    public UserModel(String username, String password, String email, String name, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
    }

    @PrePersist
    public void onCreate() {
        setId(UUID.randomUUID());
    }
}
