package main.java.com.game.item.Weapons;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Dice.DamageDice;
import main.java.com.game.item.Equipment;

public class Fist extends Equipment {
    public Fist() {
        setName("Fist");
        setDamage(new DamageDice(1, 4, 0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Кулак. Больше ничего и не надо");
    }
}

