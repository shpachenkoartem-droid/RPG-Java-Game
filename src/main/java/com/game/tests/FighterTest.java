package main.java.com.game.tests;

import main.java.com.game.model.Enemies.Goblin;

import main.java.com.game.model.Heroes.Hero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FighterTest {

    @Test
    void attack() {
    }

    @Test
    void equip() {
    }

    @Test
    void drinkPotion() {
    }

    @Test
    void getHp() {
    }

    @Test
    void setHp() {
    }

    @Test
    void newGoblin_hasFiveHp() {
        Goblin goblin = new Goblin();
        assertEquals(5, goblin.getHp());
    }

    void testSword() {
        Hero hero = new Hero();
    }
}