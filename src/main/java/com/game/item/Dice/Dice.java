package main.java.com.game.item.Dice;
import java.util.Random;


public class Dice {
    Random rnd = new Random();
    // Говорим "кидаем значение"
    public int roll(int count, int sides, int bonus) {
        if (sides < 1) {
            return 0;
        }
        int diceValue = rnd.nextInt(1, sides+1);
        return count * diceValue + bonus;
    }

}
