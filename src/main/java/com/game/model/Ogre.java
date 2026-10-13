package main.java.com.game.model;

import main.java.com.game.item.Fist;

public class Ogre extends Fighter {
    public Ogre() {
        setMax_hp(10);
        setHp(10);
        setSword(new Fist());
        setName("Огр");
        setMoney(270);
    }
}

