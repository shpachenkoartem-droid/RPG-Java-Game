package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class IronSword extends Equipment {
    public IronSword() {
        setName("Iron sword");
        setCost(50);
        getDamage().roll();
        setItemType(ItemType.SWORD);
    }
}
