package main.java.com.game.item;

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
        setName("Poison");
        setDmgAmount(5);
        setCost(40);
        setItemType(ItemType.POTION);
    }
    @Override
    public void apply (Fighter target) {
        // Если 0, то выводится сообщение что нет зелий, сделать это в отдельном методе
        if (drink()) {
            target.decHp(getDmgAmount());
        }
    }
}

