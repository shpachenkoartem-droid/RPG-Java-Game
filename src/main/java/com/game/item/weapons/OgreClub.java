package main.java.com.game.item.weapons;

import main.java.com.game.enums.ItemType;
import main.java.com.game.dice.DamageDice;
import main.java.com.game.item.Equipment;

public class OgreClub extends Equipment {
    public OgreClub() {
        setName("Дубина огра");
        setCost(0);
        setDamage(new DamageDice(1,8,0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Большая деревянная коряга, неравномерно толстая палка, но может хорошо ударить");
    }
}
