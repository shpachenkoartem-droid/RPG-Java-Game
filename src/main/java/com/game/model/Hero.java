package main.java.com.game.model;

import main.java.com.game.item.*;

public class Hero extends PlayableHeroes {
    Fist fist = new Fist();
    Skin skin = new Skin();
    // Создаём инвентарь только для одного героя
    // Все методы для воздействия реализуем в main.java.com.game.PlayableHeroes
    public Hero() {
        setMax_hp(50);
        setHp(50);
        setName("Герой");
        setMoney(1000);
        setHeal(2);
        equipSword(fist);
        equipArmor(skin);
    }
}
