package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class IronSword extends Equipment {
    public IronSword() {
        setName("Iron sword");
        setCost(50);
        setDamage(new DamageDice(1, 6, 1));
        setItemType(ItemType.SWORD);
        setDescription("Железный меч, готовый на всё.");
    }
}
