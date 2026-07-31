package main.java.com.game.item;

// Сделать чтобы снаряжение складывалось в инвентарь и оттуда доставалось. Потому что хз как по-другому сделать проверки в isPurchased
public abstract class Equipment extends Item {
    private int dmg;
    private int armor;

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
