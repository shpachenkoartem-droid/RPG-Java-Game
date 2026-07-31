package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class GoldSword extends Equipment {
    public GoldSword() {
        setName("Gold sword");
        setDmg(5);
        setCost(120);
        setItemType(ItemType.SWORD);
    }
}
