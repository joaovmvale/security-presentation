package com.securityDevelopment.equipment.repository;

import com.securityDevelopment.equipment.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<EquipmentModel, UUID> {
}
