package com.securityDevelopment.equipment.repository;

import com.securityDevelopment.equipment.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<EquipmentModel, UUID> {
    List<EquipmentModel> findByUserId(UUID userId);

    @Transactional
    void deleteByUserId(UUID userId);
}
