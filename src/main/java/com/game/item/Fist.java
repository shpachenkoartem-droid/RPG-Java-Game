package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class Fist extends Equipment {
    public Fist() {
        setName("Fist");
        setDmg(3);
        setItemType(ItemType.SWORD);
        incCount();
    }
}

