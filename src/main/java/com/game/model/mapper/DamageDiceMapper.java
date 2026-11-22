package com.game.model.mapper;

import com.game.dice.DamageDice;
import com.game.model.entity.DamageDiceEntity;

public class DamageDiceMapper {
    public static DamageDiceEntity toEntity(DamageDice dice) {
        DamageDiceEntity e = new DamageDiceEntity();

        e.setCount(dice.getCount());
        e.setSides(dice.getSides());
        e.setBonus(dice.getBonus());

        return e;
    }

    public static DamageDice toDomain(DamageDiceEntity e) {
        return new DamageDice(
                e.getCount(),
                e.getSides(),
                e.getBonus()
        );
    }
}