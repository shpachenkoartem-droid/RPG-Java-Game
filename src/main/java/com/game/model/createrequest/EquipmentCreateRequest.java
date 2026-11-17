package com.game.model.createrequest;

import com.game.model.entity.DamageDiceEntity;

public record EquipmentCreateRequest(
        int armorCount,
        DamageDiceEntity damage
) {}
