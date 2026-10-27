package com.game.item.weapons;

import com.game.enums.ItemType;
import com.game.model.heroes.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldSwordTest {
    private Hero hero;
    private GoldSword goldSword;

    @BeforeEach
    void setUp() {
        hero = new Hero();
        goldSword = new GoldSword();
    }

    @Test
    void isSword() {
        assertEquals(ItemType.SWORD, goldSword.getItemType());
    }

    @Test
    void damage_alwaysInRange() {
        for (int i = 0; i < 100; i++) {
            int damage = goldSword.getDamage().roll();
            assertTrue(damage >= 3 && damage <= 8, "Урон вне диапазона: " + damage);
        }
    }

    @Test
    void canBeEquipped() {
        hero.equip(goldSword);
        assertEquals(goldSword, hero.getSword());
    }
}