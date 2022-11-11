package com.securityDevelopment.user.service;

import com.securityDevelopment.user.model.UserModel;
import com.securityDevelopment.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public List<UserModel> listAll() {
        return userRepository.findAll();
    }

    public UserModel findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
