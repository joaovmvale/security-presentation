package com.securityDevelopment.user.repository;

import com.securityDevelopment.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    public Optional<UserModel> findByUsername(String username);
}
