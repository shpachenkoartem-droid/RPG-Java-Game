package com.game.model.mapper;

import com.game.factory.EquipmentFactory;
import com.game.item.Equipment;
import com.game.model.entity.DamageDiceEntity;
import com.game.model.entity.EquipmentEntity;

public class EquipmentMapper {
    public static EquipmentEntity toEntity(Equipment equipment) {
        EquipmentEntity e = new EquipmentEntity();

        e.setArmorCount(equipment.getArmorCount());
        e.setDamage(new DamageDiceEntity());

        return e;
    }

    public static Equipment toDomain(EquipmentEntity e) {
        Equipment equipment = EquipmentFactory.create(e.getType());

        equipment.setArmorCount(e.getArmorCount());
        // Здесь мы взяли damage из DamageDiceEntity и с помощью маппера сделали игровым классом
        equipment.setDamage(DamageDiceMapper.toDomain(e.getDamage()));

        return equipment;
    }
}