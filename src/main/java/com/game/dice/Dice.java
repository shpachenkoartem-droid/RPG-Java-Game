package com.game.dice;
import java.util.Random;


public class Dice {
    Random rnd = new Random();
    // Говорим "кидаем значение"
    // Возвращает число, которое выпало бросками и модификаторами кубика
    // Например, 1d4+1, выпало 3
    // Это 3+1=4
    public int roll(int count, int sides, int bonus) {
        int result = 0;
        if (sides < 1) {
            return 0;
        }
        for (int i = 0; i < count; i++) {
            int diceValue = rnd.nextInt(1, sides+1);
            result+=diceValue;
        }
        result+=bonus;
        return result;
    }

}
