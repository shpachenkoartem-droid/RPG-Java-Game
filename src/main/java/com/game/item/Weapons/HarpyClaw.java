package main.java.com.game.item.Weapons;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Dice.DamageDice;
import main.java.com.game.item.Equipment;

public class HarpyClaw extends Equipment {
    public HarpyClaw() {
        setName("Когти гарпии");
        setDamage(new DamageDice(1, 4, 4));
        // по идее в предметах урон не нужен, цифры урона сделать
        // оружие должно передавать атрибуты и урон считается автоматически
        setCost(0);
        setItemType(ItemType.SWORD);
        setDescription("Острые как бритва когти.");
    }
}
