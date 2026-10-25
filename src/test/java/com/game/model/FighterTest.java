package test.java.com.game.model;

import main.java.com.game.model.enemies.Goblin;

import main.java.com.game.model.heroes.Hero;
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
    void newGoblinHasFiveHp() {
        Goblin goblin = new Goblin();
        assertEquals(5, goblin.getHp());
    }

    void testSword() {
        Hero hero = new Hero();
    }
}