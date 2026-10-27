package com.game.item.potions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealPotionTest {
    private HealPotion healPotion;

    @BeforeEach
    void setUp() {
        healPotion = new HealPotion();
    }

    @Test
    void afterDrinkCountZero() {
        healPotion.drink();
        assertEquals(0, healPotion.getCount());
    }
}