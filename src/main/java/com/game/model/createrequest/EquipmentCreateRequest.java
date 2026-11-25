package com.game.model.createrequest;

import com.game.enums.EquipmentType;

// Клиент будет говорить серверу "создай железный меч"
public record EquipmentCreateRequest(
        int armorCount,
        EquipmentType type,
        Long damageDiceId
) {}