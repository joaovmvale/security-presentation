package com.securityDevelopment.equipment.model.dto;

import com.securityDevelopment.equipment.model.EquipmentModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EquipmentResponseDTO {
    private UUID id;
    private UUID serial;
    private String model;
    private String memory;
    private String processor;
    private String storage;

    public static EquipmentResponseDTO transformToEquipmentResponseDTO(EquipmentModel equipment) {
        return new EquipmentResponseDTO(equipment.getId(), equipment.getSerial(), equipment.getModel(), equipment.getMemory(), equipment.getProcessor(), equipment.getStorage());
    }
}
