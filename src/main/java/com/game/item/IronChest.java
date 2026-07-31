package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class IronChest extends Equipment {
    public IronChest() {
        setName("IronChest");
        setCost(200);
        setArmor(1);
        setItemType(ItemType.ARMOR);
    }
}

