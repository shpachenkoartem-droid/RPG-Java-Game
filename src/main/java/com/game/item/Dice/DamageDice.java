package main.java.com.game.item.Dice;

public class DamageDice {
    Dice dice = new Dice();


    private int count;
    private int sides;
    private int bonus;

    public DamageDice(int count, int sides, int bonus) {
        this.count = count;
        this.sides = sides;
        this.bonus = bonus;
    }


    public int roll() {
        return dice.roll(count, sides, bonus);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
