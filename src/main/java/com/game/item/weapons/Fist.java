package com.game.item.weapons;

import com.game.enums.ItemType;
import com.game.dice.DamageDice;
import com.game.item.Equipment;

public class Fist extends Equipment {
    public Fist() {
        setName("Кулак");
        setDamage(new DamageDice(1, 4, 0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Кулак. Больше ничего и не надо");
    }
}

