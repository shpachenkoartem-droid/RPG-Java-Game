package main.java.com.game.model.Heroes;

import main.java.com.game.item.Chests.Skin;
import main.java.com.game.item.Weapons.Fist;

public class Hero extends PlayableHeroes {
    // Создаём инвентарь только для одного героя
    // Все методы для воздействия реализуем в main.java.com.game.PlayableHeroes
    public Hero() {
        setMaxHp(50);
        setHp(50);
        setName("Герой");
        setMoney(1000);
        setHeal(2);
        equip(new Fist());
        equip(new Skin());
    }
}
