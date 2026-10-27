package com.game.model;

import com.game.model.enemies.Goblin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FighterTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void newGoblinHasFiveHp() {
        Goblin goblin = new Goblin();
        assertEquals(5, goblin.getHp());
    }

}