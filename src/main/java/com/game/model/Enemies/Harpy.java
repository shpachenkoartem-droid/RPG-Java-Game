package main.java.com.game.model.Enemies;

import main.java.com.game.item.Weapons.HarpyClaw;

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