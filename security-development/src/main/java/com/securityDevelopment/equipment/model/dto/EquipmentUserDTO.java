package com.securityDevelopment.equipment.model.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EquipmentUserDTO {
    private UUID serial;
    private String model;
    private String memory;
    private String processor;
    private String storage;
    private UUID userId;
}
