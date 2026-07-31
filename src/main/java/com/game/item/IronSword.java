package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class IronSword extends Equipment {
    public IronSword() {
        setName("Iron sword");
        setDmg(4);
        setCost(50);
        setItemType(ItemType.SWORD);
    }
}
