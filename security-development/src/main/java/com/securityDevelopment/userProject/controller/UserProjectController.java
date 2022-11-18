package com.securityDevelopment.userProject.controller;

import com.securityDevelopment.userProject.model.UserProjectModel;
import com.securityDevelopment.userProject.model.dto.UserProjectDTO;
import com.securityDevelopment.userProject.model.dto.UserProjectResponseDTO;
import com.securityDevelopment.userProject.service.UserProjectService;
import com.securityDevelopment.utils.exception.CustomException;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SecurityScheme(name = "Bearer", type = SecuritySchemeType.HTTP, bearerFormat = "jwt", scheme = "bearer")
@SecurityRequirement(name = "Bearer")
@RestController
@RequestMapping("/api/user-project")
public class UserProjectController {
    private final AmqpTemplate queueSender;
    UserProjectService userProjectService;

    @Autowired
    public UserProjectController(UserProjectService userProjectService, AmqpTemplate queueSender) {
        this.userProjectService = userProjectService;
        this.queueSender = queueSender;
    }

    @PostMapping
    public ResponseEntity<UserProjectResponseDTO> associateUserToProject(@RequestBody UserProjectDTO dto) throws CustomException {
        UserProjectModel userProjectModel = userProjectService.associateUserToProject(dto.transformToUserProjectModel());
        UserProjectResponseDTO response = UserProjectResponseDTO.transformToUserProjectResponseDTO(userProjectModel);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "Create userProject");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserProjectResponseDTO>> listAll() {
        List<UserProjectModel> userProjectModels = userProjectService.listAll();
        List<UserProjectResponseDTO> response = userProjectModels.stream().map(UserProjectResponseDTO::transformToUserProjectResponseDTO).collect(Collectors.toList());
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "List userProject");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProjectResponseDTO> findById(@PathVariable UUID id) {
        UserProjectModel userProjectModel = userProjectService.getById(id);
        UserProjectResponseDTO response = UserProjectResponseDTO.transformToUserProjectResponseDTO(userProjectModel);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "List by id userProject");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProjectResponseDTO> update(@PathVariable UUID id, @RequestBody UserProjectDTO dto) throws CustomException {
        UserProjectModel userProjectModel = userProjectService.getById(id);
        userProjectModel.setProjectId(dto.getProjectId());
        userProjectModel.setUserId(dto.getUserId());

        userProjectService.create(userProjectModel);
        UserProjectResponseDTO response = UserProjectResponseDTO.transformToUserProjectResponseDTO(userProjectModel);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "Edit userProject");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userProjectService.deleteById(id);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "Delete userProject");
        return ResponseEntity.noContent().build();
    }

}
