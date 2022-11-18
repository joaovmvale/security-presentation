package com.securityDevelopment.userProject.repository;

import com.securityDevelopment.userProject.model.UserProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProjectRepository extends JpaRepository<UserProjectModel, UUID> {
}
