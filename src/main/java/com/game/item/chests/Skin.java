package main.java.com.game.item.chests;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Equipment;

public class Skin extends Equipment {
    public Skin() {
        setName("Кожа");
        setArmorCount(0);
        setItemType(ItemType.ARMOR);
        incCount();
    }
}

