package main.java.com.game.model;

import main.java.com.game.model.PlayableHeroes;

public class Paladin extends main.java.com.game.model.PlayableHeroes {
    public Paladin() {
        setMax_hp(55);
        setHp(55);
        setDmg(2);
        setName("Паладин");
        setMoney(10);
        setHeal(4);
    }
}
