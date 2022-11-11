package com.projectService.project.model;

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
@Table(name = "PROJECT")
public class ProjectModel {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "size", nullable = false)
    private Number size;

    public ProjectModel(String name, String status, Number size) {
        this.name = name;
        this.status = status;
        this.size = size;
    }

    @PrePersist
    protected void onCreate() {
        setId(UUID.randomUUID());
    }
}
