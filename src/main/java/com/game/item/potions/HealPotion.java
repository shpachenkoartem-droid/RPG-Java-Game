package main.java.com.game.item.potions;

import main.java.com.game.enums.ItemType;
import main.java.com.game.model.*;


public class HealPotion extends Potion {
    private int healAmount = 0;
    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
    // Что за зелье и что оно делает
    public HealPotion() {
        setName("Зелье лечения");
        setHealAmount(8);
        setCost(30);
        setItemType(ItemType.POTION);
        setSubType(ItemType.HEAL_POTION);
        setDescription("Зелье лечения, способное вытащить из самой непростой передряги");
    }
    // Его непосредственное применение
    @Override
    public void apply(Fighter target) {
        // Если уменьшили количество
        if (drink()) {
            // Реализация того что делает зелье
            target.incHp(getHealAmount());
        }
    }
}

