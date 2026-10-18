package main.java.com.game.item.Potions;

import main.java.com.game.enums.ItemType;
import main.java.com.game.model.*;

public class Poison extends Potion {
    private int dmgAmount = 0;

    public int getDmgAmount() {
        return dmgAmount;
    }

    public void setDmgAmount(int dmgAmount) {
        this.dmgAmount = dmgAmount;
    }

    public Poison() {
        setName("Яд");
        setDmgAmount(5);
        setCost(40);
        setItemType(ItemType.POTION);
        setSubType(ItemType.POISON);
        setDescription("Смертельная отрава для неугодных");
    }

    @Override
    public void apply (Fighter target) {
        if (drink()) {
            target.decHp(getDmgAmount());
        }
    }
}

