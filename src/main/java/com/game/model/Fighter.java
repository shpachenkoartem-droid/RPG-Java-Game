package main.java.com.game.model;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.*;

import java.util.Objects;

// абстрактный класс бойца - "образ" всех кто должен сражаться
public abstract class Fighter {
    private int hp;
    private int max_hp;
    private int dmg;
    private String name;
    private int money;
    // Чтобы не нарушать принцип подстановки Барбары Лисков (LSP) надо сделать класс наследника, который может реализовывать лечение (герой, враг-жрец и тп)
    // Потому что не каждый боец может лечить
    private int reduce_damage;
    private Equipment sword;
    private Equipment armor;

    // Возвращаем урон, который наносится
    public int attack(Fighter target) {
        int damage = Math.max(0, getDmg() - target.getReduceDamage());
        target.decHp(damage);
        return damage;
    }

    // Как работает экипировка мечей
    // С помощью конструкторов мы создаём каждый меч
    // main.java.com.game.Fighter получает метод equipSword где в параметрах указывается абстрактный класс всех предметов main.java.com.game.Equipment, а значит и в частности наш меч
    // Поставили урон с помощью set, "протащили" значение от нашего конструктора меча, подход вроде правильный, но не уверен
    public void equip(Equipment equipment) {
        if (equipment.isNotNull()) {
            if (Objects.equals(equipment.getItemType(), ItemType.SWORD)) {
                this.sword = equipment;
                setDmg(equipment.getDmg());
            }
            if (Objects.equals(equipment.getItemType(), ItemType.ARMOR)) {
                this.armor = equipment;
                setArmor(equipment);
            }
        }
        else { System.out.println("У вас нет такого" + equipment.getItemType()); }
    }


    public void drinkPotion(Potion potion) {
        // Зелье применяется на выпившего его
        potion.apply(this);
    }


// Геттеры и сеттеры нужны чтобы получать и изменять значения хп, атаки и так далее для моих бойцов из любого места
// Благодаря ним код следует принципам ИНКАПСУЛЯЦИИ
// Так как в main.java.com.game.Fighter поля private, то их нельзя изменить с помощью простого hero.hp = hero.hp + 5
// Теперь поля можно изменять с помощью hero.setHp(hero.getHp() + 5)
// К ним можно добавить защиту от отрицательного хп, слишком большого урона и бессмыслицы

// У нас есть класс в котором мы определили поля для него самого и всех дочерних классов.
// Дочерние классы более индивидуальны, для них уже выбираем конкретные значения.
// В других классах создав объект этих дочерних можно менять каждый объект отдельно

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            System.out.print("Здоровье должно быть больше 0");
        } else { this.hp = hp; }

    }

    public void incHp(int hp) {
        setHp(this.getHp() + hp);
        // Защита от превышения максимального здоровья
        if (getHp() > getMax_hp()) {
            setHp(getMax_hp());
        }
    }

    public void decHp(int hp) {
        if (getHp() - hp >= 0) {
            setHp(this.getHp() - hp);
        } else {
            setHp(0);
        }
    }

    public int getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getDmg() {
        return dmg;
    }

    /*
    public Equipment getNeedForEquip(Equipment equipment) {
        if (Objects.equals(equipment.getItemType(), ItemType.SWORD)) {
            return sword;
        }
        if (Objects.equals(equipment.getItemType(), ItemType.ARMOR)) {
            return armor;
        }
        return test;
    }

    public void setNeedForEquip(Equipment equipment) {
        if (Objects.equals(equipment.getItemType(), ItemType.SWORD)) {
            setDmg(equipment.getDmg());
        }
        if (Objects.equals(equipment.getItemType(), ItemType.ARMOR)) {
            setReduceDamage(equipment.getArmor());
        }
    }

     */

    public void setDmg(int dmg) {
        if (dmg <= 0) { System.out.print("Урон должен быть больше 0"); }
        else { this.dmg = dmg; }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // Хочу чтобы getMoney вызывалась "под капотом", и вместо fighter1.setMoney(fighter1.getMoney() + 10) было fighter1.incMoney(10)
    // this обозначает вызвавшего
    public void incMoney(int money) {
        setMoney(this.getMoney() + money);
    }

    public void decMoney(int money) {
        if (getMoney() - money >= 0) {
            setMoney(this.getMoney() - money);
        } else {
            System.out.print("Недостаточно денег");
        }
    }

    public Equipment getSword() {
        return sword;
    }

    public void setSword(Equipment sword) {
        this.sword = sword;
    }

    public Equipment getArmor() {
        return armor;
    }

    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    public int getReduceDamage() {
        return reduce_damage;
    }

}