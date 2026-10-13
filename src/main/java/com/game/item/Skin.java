package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class Skin extends Equipment {
    public Skin() {
        setName("Skin");
        setArmorCount(0);
        setItemType(ItemType.ARMOR);
        incCount();
    }
}

