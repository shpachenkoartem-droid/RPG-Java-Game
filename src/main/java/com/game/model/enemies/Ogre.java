package main.java.com.game.model.enemies;

import main.java.com.game.item.weapons.OgreClub;

public class Ogre extends Enemy {
    public Ogre() {
        setMaxHp(10);
        setHp(10);
        setSword(new OgreClub());
        setName("Огр");
        setMoney(270);
        setDrop(new OgreClub(), 20);
    }
}

