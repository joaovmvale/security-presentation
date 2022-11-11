package com.securityDevelopment.equipment.service;

import com.securityDevelopment.equipment.model.EquipmentModel;
import com.securityDevelopment.equipment.repository.EquipmentRepository;
import com.securityDevelopment.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public EquipmentModel create(EquipmentModel equipment) throws CustomException {
        if (equipment.getModel() == null || equipment.getModel().equals("") || equipment.getModel().length() > 300) {
            throw new CustomException("ERR001", "Equipment model is wrong.");
        }
        return equipmentRepository.save(equipment);
    }

    public List<EquipmentModel> listAll() {
        return equipmentRepository.findAll();
    }

    public EquipmentModel getById(UUID id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        equipmentRepository.deleteById(id);
    }

}


