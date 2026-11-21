package com.game.service;

import com.game.factory.EquipmentFactory;
import com.game.item.Equipment;
import com.game.model.createrequest.EquipmentCreateRequest;
import com.game.model.entity.EquipmentEntity;
import com.game.model.mapper.EquipmentMapper;
import com.game.model.response.EquipmentResponse;
import com.game.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public EquipmentResponse create(EquipmentCreateRequest request) {
        // Передаём через фабрику какой конкретно будет предмет
        Equipment equipment = EquipmentFactory.create(request.type());

        EquipmentEntity entity = EquipmentMapper.toEntity(equipment);

        EquipmentEntity saved = equipmentRepository.save(entity);

        return toResponse(saved);
    }

    public List<EquipmentResponse> findAll() {
        return equipmentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}
