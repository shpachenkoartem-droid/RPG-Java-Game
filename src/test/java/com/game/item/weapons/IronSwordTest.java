package com.game.item.weapons;

import com.game.enums.ItemType;
import com.game.model.heroes.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IronSwordTest {
    private Hero hero;
    private IronSword ironSword;

    @BeforeEach
    void setUp() {
        hero = new Hero();
        ironSword = new IronSword();
    }

    @Test
    void isSword() {
        assertEquals(ItemType.SWORD, ironSword.getItemType());
    }

    @Test
    void damage_alwaysInRange() {
        for (int i = 0; i < 100; i++) {
            int damage = ironSword.getDamage().roll();
            assertTrue(damage >= 2 && damage <= 7, "Урон вне диапазона: " + damage);
        }
    }

    @Test
    void canBeEquipped() {
        hero.equip(ironSword);
        assertEquals(ironSword, hero.getSword());
    }

}