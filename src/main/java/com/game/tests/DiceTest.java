package main.java.com.game.tests;

import main.java.com.game.item.Dice;
import org.junit.jupiter.api.Test;

class DiceTest {

    @Test
    void roll() {
        Dice dice = new Dice();
        System.out.println(dice.roll(1,-1,0));
    }
    @Test
    void print4() {
        System.out.println(4);
    }
}