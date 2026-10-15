package main.java.com.game.model.Enemies;

import main.java.com.game.item.Weapons.OgreClub;

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

