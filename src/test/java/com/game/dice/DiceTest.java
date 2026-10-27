package com.game.dice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class DiceTest {
    private Dice dice;
    private int result;

    @BeforeEach
    void setUp() {
        dice = new Dice();
    }

    @Test
    void simpleRoll() {
        result = dice.roll(5,1,0);
        assertEquals(5, result);
    }

    @Test
    void simpleRollWithBonus() {
        result = dice.roll(5,1,3);
        assertEquals(8, result);
    }

    @Test
    void manyCountRoll() {
        result = dice.roll(3,2,3);
        // Передаём набор подходящих символов и
        assertTrue(Set.of(6,7,8,9).contains(result), "Ожидалось одно из 6,7,8,9, но было: " + result);
    }
}