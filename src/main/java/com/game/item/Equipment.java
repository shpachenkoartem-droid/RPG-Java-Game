package main.java.com.game.item;


// Сделать чтобы снаряжение складывалось в инвентарь и оттуда доставалось. Потому что хз как по-другому сделать проверки в isPurchased
public abstract class Equipment extends Item  {

    DamageDice damage = new DamageDice();
    private int dmg;
    private int armorCount;

    // Вместо setDamage()
    // getDamage().roll()
    public DamageDice getDamage() {
        return damage;
    }

    public void setDamage(DamageDice damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armorCount;
    }

    public void setArmor(int armorCount) {
        this.armorCount = armorCount;
    }
}