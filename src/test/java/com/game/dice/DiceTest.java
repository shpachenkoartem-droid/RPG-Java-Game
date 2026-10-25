package test.java.com.game.dice;

import main.java.com.game.dice.Dice;
import org.junit.jupiter.api.Test;

class DiceTest {

    @Test
    void roll() {
        Dice dice = new Dice();
        System.out.println(dice.roll(1,2,0));
    }

    @Test
    void print4() {
    }
}