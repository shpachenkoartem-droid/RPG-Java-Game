package com.game.item.weapons;

import com.game.enums.ItemType;
import com.game.dice.DamageDice;
import com.game.item.Equipment;

public class IronSword extends Equipment {
    public IronSword() {
        setName("Железный меч");
        setCost(50);
        setDamage(new DamageDice(1, 6, 1));
        setItemType(ItemType.SWORD);
        setDescription("Железный меч, готовый на всё.");
    }
}
