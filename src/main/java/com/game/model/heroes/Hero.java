package com.game.model.heroes;

import com.game.item.chests.Skin;
import com.game.item.weapons.Fist;

public class Hero extends PlayableHeroes {
    // Создаём инвентарь только для одного героя
    // Все методы для воздействия реализуем в com.game.PlayableHeroes
    public Hero() {
        setMaxHp(50);
        setHp(50);
        setName("Герой");
        setMoney(10);
        setHeal(2);
        equip(new Fist());
        equip(new Skin());
    }
}
