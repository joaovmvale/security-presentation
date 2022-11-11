package com.securityDevelopment.equipment.controller;

import com.securityDevelopment.equipment.model.EquipmentModel;
import com.securityDevelopment.equipment.model.dto.EquipmentDTO;
import com.securityDevelopment.equipment.model.dto.EquipmentResponseDTO;
import com.securityDevelopment.equipment.service.EquipmentService;
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
@RequestMapping("/api/equipment")
public class EquipmentController {
    EquipmentService equipmentService;
    @Autowired
    public EquipmentController(EquipmentService equipmentService, AmqpTemplate queueSender) {
        this.equipmentService = equipmentService;
        this.queueSender = queueSender;
    }
    private final AmqpTemplate queueSender;

    @PostMapping
    public ResponseEntity<EquipmentResponseDTO> create(@RequestBody EquipmentDTO dto) throws CustomException {
        EquipmentModel equipment = equipmentService.create(dto.transformToEquipmentModel());
        EquipmentResponseDTO response = EquipmentResponseDTO.transformToEquipmentResponseDTO(equipment);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "Create equipments");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponseDTO>> listAll() {
        List<EquipmentModel> equipment = equipmentService.listAll();
        List<EquipmentResponseDTO> response = equipment.stream().map(EquipmentResponseDTO::transformToEquipmentResponseDTO).collect(Collectors.toList());
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "List equipments");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> findById(@PathVariable UUID id) {
        EquipmentModel equipment = equipmentService.getById(id);
        EquipmentResponseDTO response = EquipmentResponseDTO.transformToEquipmentResponseDTO(equipment);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "List by id equipments");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> update(@PathVariable UUID id, @RequestBody EquipmentDTO dto) throws CustomException {
        EquipmentModel equipment = equipmentService.getById(id);
        equipment.setSerial(dto.getSerial());
        equipment.setModel(dto.getModel());
        equipment.setMemory(dto.getMemory());
        equipment.setProcessor(dto.getProcessor());
        equipment.setStorage(dto.getStorage());

        equipmentService.create(equipment);
        EquipmentResponseDTO response = EquipmentResponseDTO.transformToEquipmentResponseDTO(equipment);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "Edit equipments");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        equipmentService.deleteById(id);
        queueSender.convertAndSend("securityDev-exchange", "securityDev-routing-key", "Delete equipments");
        return ResponseEntity.noContent().build();
    }

}
