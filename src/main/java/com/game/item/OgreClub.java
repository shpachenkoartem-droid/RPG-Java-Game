package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class OgreClub extends Equipment {
    public OgreClub() {
        setName("Ogre Club");
        setCost(0);
        setDamage(new DamageDice(1,8,0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Большая деревянная коряга, неравномерно толстая палка, но может хорошо ударить");
    }
}
