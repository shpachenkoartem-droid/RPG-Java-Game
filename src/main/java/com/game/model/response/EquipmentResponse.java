package com.game.model.response;

import com.game.model.entity.DamageDiceEntity;

public record EquipmentResponse(
        Long id,
        int armorCount,
        DamageDiceEntity damage
) {}