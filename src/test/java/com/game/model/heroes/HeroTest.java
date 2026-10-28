package com.game.model.heroes;

import com.game.item.Equipment;
import com.game.item.weapons.IronSword;
import com.game.model.enemies.Goblin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    private Hero hero;
    private Goblin goblin;
    private Equipment ironSword;

    @BeforeEach
    void setUpHero() {
        hero = new Hero();
        goblin = new Goblin();
        ironSword = new IronSword();
        ironSword.incCount();
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

    @Test
    void incMoney() {
        int current = hero.getMoney();
        hero.incMoney(1000);
        assertEquals(current + 1000, hero.getMoney());
    }

    @Test
    void decManyMoney() {
        int current = hero.getMoney();
        hero.decMoney(10000);
        assertEquals(current, hero.getMoney());
    }

    @Test
    void decMoney() {
        hero.decMoney(hero.getMoney());
        assertEquals(0, hero.getMoney());
    }

    // Списывается ли нужное количество хп при попадании
    @Test
    void attack() {
        hero.attack(goblin);
        int t = goblin.getMaxHp() - goblin.getHp();
        assertEquals(t, hero.getIntDamage());
    }

    @Test
    void equipSword() {
        hero.equip(ironSword);
        assertEquals(ironSword, hero.getSword());
    }
}