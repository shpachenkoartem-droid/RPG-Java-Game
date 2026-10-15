package main.java.com.game.model;

import main.java.com.game.item.OgreClub;

public class Ogre extends Enemy {
    public Ogre() {
        setMax_hp(10);
        setHp(10);
        setSword(new OgreClub());
        setName("Огр");
        setMoney(270);
        setDrop(new OgreClub(), 20);
    }
}

