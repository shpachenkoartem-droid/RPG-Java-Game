package main.java.com.game.item;
import java.util.Random;


public class Dice {
    Random rnd = new Random();

    // Говорим "кидаем значение"
    public int roll(int count, int sides, int bonus) {
        int diceValue = rnd.nextInt(1, sides+1);
        return count * diceValue + bonus;
    }

}
