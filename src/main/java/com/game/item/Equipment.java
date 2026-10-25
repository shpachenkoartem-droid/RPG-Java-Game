package main.java.com.game.item;


import main.java.com.game.item.Dice.DamageDice;

public abstract class Equipment extends Item  {

    DamageDice damage = new DamageDice(0, 0, 0);
    private int armorCount;

    // Метод даёт набор из 3х значений, по которым считается урон
    // Для вывода не подходит, даёт вид main.java.com.game.item.Dice.DamageDice@4802796d
    public DamageDice getDamage() {
        return damage;
    }

    public void setDamage(DamageDice damage) {
        this.damage = damage;
    }

    public int getArmorCount() {
        return armorCount;
    }

    public void setArmorCount(int armorCount) {
        this.armorCount = armorCount;
    }
}