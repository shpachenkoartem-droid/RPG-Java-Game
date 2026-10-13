package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

public class GoldSword extends Equipment {
    public GoldSword() {
        setName("Gold sword");
        setDamage(new DamageDice(1, 6, 2));
        // по идее в предметах урон не нужен, цифры урона сделать
        // оружие должно передавать атрибуты и урон считается автоматически
        setCost(120);
        setItemType(ItemType.SWORD);
    }
}
