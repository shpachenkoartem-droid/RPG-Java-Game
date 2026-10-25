package main.java.com.game.model.enemies;

import main.java.com.game.item.weapons.HarpyClaw;

public class Harpy extends Enemy {
    public Harpy() {
        setMaxHp(13);
        setHp(13);
        setSword(new HarpyClaw());
        setName("Гарпия");
        setMoney(120);
        setDrop(new HarpyClaw(), 25);
    }
}