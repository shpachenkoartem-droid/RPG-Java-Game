package com.game.model.heroes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    private Hero hero;
    @BeforeEach
    void setUpHero(){
        hero = new Hero();
    }

    @Test
    void increaseMoreMaxHp() {
        hero.incHp(100);
        assertEquals(50, hero.getHp());
    }

    @Test
    void decreaseMoreMinHp() {
        hero.decHp(100);
        assertEquals(0, hero.getHp());
    }
}