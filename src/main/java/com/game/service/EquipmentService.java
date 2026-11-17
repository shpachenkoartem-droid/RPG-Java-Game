package com.game.service;

import com.game.item.Equipment;
import com.game.model.createrequest.EquipmentCreateRequest;
import com.game.model.entity.EquipmentEntity;
import com.game.model.response.EquipmentResponse;
import com.game.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    private EquipmentResponse toResponse(EquipmentEntity entity) {
        return new EquipmentResponse(
                entity.getId(),
                entity.getArmorCount(),
                entity.getDamage()
        );
    }

    /*public EquipmentResponse create(EquipmentCreateRequest request) {
        Equipment equipment = new
    }*/
}
