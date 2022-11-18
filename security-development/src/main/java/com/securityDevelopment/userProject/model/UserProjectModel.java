package com.securityDevelopment.userProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_project")
public class UserProjectModel {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    public UserProjectModel(UUID userId, UUID projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    @PrePersist
    protected void onCreate() {
        setId(UUID.randomUUID());
    }
}
