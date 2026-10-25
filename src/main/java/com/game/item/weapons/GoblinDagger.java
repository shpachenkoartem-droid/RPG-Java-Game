package com.game.item.weapons;

import com.game.enums.ItemType;
import com.game.dice.DamageDice;
import com.game.item.Equipment;

public class GoblinDagger extends Equipment {
    public GoblinDagger() {
        setName("Кинжал гоблина");
        setCost(0);
        setDamage(new DamageDice(1,6,0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Старый скрюченный кинжал, отобранный у побеждённого существа");
    }
}
