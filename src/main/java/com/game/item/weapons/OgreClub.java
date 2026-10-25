package com.game.item.weapons;

import com.game.enums.ItemType;
import com.game.dice.DamageDice;
import com.game.item.Equipment;

public class OgreClub extends Equipment {
    public OgreClub() {
        setName("Дубина огра");
        setCost(0);
        setDamage(new DamageDice(1,8,0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Большая деревянная коряга, неравномерно толстая палка, но может хорошо ударить");
    }
}
