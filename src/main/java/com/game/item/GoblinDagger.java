package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class GoblinDagger extends Equipment {
    public GoblinDagger() {
        setName("Goblin Dagger");
        setCost(0);
        setDamage(new DamageDice(1,6,0));
        setItemType(ItemType.SWORD);
        incCount();
        setDescription("Старый скрюченный кинжал, отобранный у побеждённого существа");
    }
}
