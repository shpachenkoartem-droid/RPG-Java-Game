package main.java.com.game.item.weapons;

import main.java.com.game.enums.ItemType;
import main.java.com.game.dice.DamageDice;
import main.java.com.game.item.Equipment;

public class GoldSword extends Equipment {
    public GoldSword() {
        setName("Золотой меч");
        setDamage(new DamageDice(1, 6, 2));
        // по идее в предметах урон не нужен, цифры урона сделать
        // оружие должно передавать атрибуты и урон считается автоматически
        setCost(120);
        setItemType(ItemType.SWORD);
        setDescription("Золотой меч, слабо блестящий на свету.");
    }
}
