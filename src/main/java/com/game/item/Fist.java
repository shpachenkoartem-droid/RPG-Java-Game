package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class Fist extends Equipment {
    public Fist() {
        setName("Fist");
        setDamage(new DamageDice(1, 4, 0));
        setItemType(ItemType.SWORD);
        incCount();
    }
}

