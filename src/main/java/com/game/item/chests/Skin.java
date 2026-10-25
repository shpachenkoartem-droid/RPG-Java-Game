package com.game.item.chests;

import com.game.enums.ItemType;
import com.game.item.Equipment;

public class Skin extends Equipment {
    public Skin() {
        setName("Кожа");
        setArmorCount(0);
        setItemType(ItemType.ARMOR);
        incCount();
    }
}

