package main.java.com.game.view;

import main.java.com.game.enums.ItemType;
import main.java.com.game.model.*;
import main.java.com.game.item.Item;

import java.util.List;

// Начинаю реализацию MVC - Model main.java.com.game.Controller main.java.com.game.View
// Класс main.java.com.game.View отвечает за все выводимые данные, то есть каждые print будет тут
// Класс Model не будет знать как выводить, он лишь будет передавать данные
// Все вызовы будет производить main.java.com.game.Controller, Model лишь будет выполнять техническую часть
public class View {
    public void maimMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Создать битву");
        System.out.println("2. Сменить локацию");
        System.out.println("3. Закупиться в магазине");
        System.out.println("4. Экипировать снаряжение");
        System.out.println("5. Вывести информацию о герое");
        System.out.println("0. Выйти");
    }
    public void showChooseFighters() {
        System.out.println("\nВыберите с кем создать битву");
        System.out.println("1. Огр");
        System.out.println("2. Гоблин");
    }
    public void showChooseLocation() {
        System.out.println("\n1. Магазин");
        System.out.println("2. Лес");
    }
    public void showChooseEquip() {
        System.out.println("\nВот имеющееся снаряжение: ");
        System.out.println("1. Поменять оружие");
        System.out.println("2. Поменять броню");
    }
    public void showStats(PlayableHeroes hero) {
        System.out.println("1. Имя: " + hero.getName());
        System.out.println("2. HP: " + hero.getHp());
        System.out.println("3. Max_HP: " + hero.getMax_hp());
        System.out.println("4. Урон:" + hero.getDmg());
        System.out.println("5. Деньги: " + hero.getMoney());
        System.out.println("6. Меч: " + hero.getSword().getName());
        System.out.println("7. Броня: " + hero.getArmor().getName());
        System.out.println("8. Сопротивление урону: " + hero.getReduce_damage() + "\n");
    }
    public void showFighterName(Fighter fighter) {System.out.println("\nИмя: " + fighter.getHp());}
    public void showHP(Fighter fighter) {System.out.println("\nHP: " + fighter.getHp());}
    public void showMaxHP(Fighter fighter) {System.out.println("\nMax_HP: " + fighter.getMax_hp());}
    public void showDmg(Fighter fighter) {System.out.println("\nУрон: " + fighter.getDmg());}
    public void showMoney(Fighter fighter) {System.out.println("\nДеньги: " + fighter.getMoney());}
    public void showSword(Fighter fighter) {System.out.println("\nМеч: " + fighter.getSword());}
    public void showArmor(Fighter fighter) {System.out.println("\nБроня: " + fighter.getArmor());}
    public void showReduceDamage(Fighter fighter) {System.out.println("\nСнижение урона: " + fighter.getReduce_damage());}
    public void showItemName(Item item) {System.out.println("\nИмя предмета: " + item.getName());}
    public void showCost(Item item) {System.out.println("\nСтоимость предмета: " + item.getCost());}
    public void showCount(Item item) {System.out.println("\nКоличество предмета: " + item.getCount());}
    public void here() {System.out.println("\nВот тут! Прямо здесь! Прямо в этой части кода!");}
    public void wrong() {System.out.println("\nВведите правильное значение.");}

    public void showPotionList(List<Item> potions) {
        if (potions.isEmpty()) {
            System.out.println("У вас нет зелий.");
            return;
        }
        System.out.println("Ваши зелья:");
        for (int i = 0; i < potions.size(); i++) {
            Item p = potions.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void showEquipmentList(List<Item> equipmentType, ItemType itemType) {
        if (equipmentType.isEmpty()) {
            System.out.println("У вас нет " + itemType);
            return;
        }
        System.out.println("Ваши " + itemType + ":");
        for (int i = 0; i < equipmentType.size(); i++) {
            Item p = equipmentType.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void showSwordList(List<Item> swords) {
        if (swords.isEmpty()) {
            System.out.println("У вас нет мечей.");
            return;
        }
        System.out.println("Ваши мечи:");
        for (int i = 0; i < swords.size(); i++) {
            Item p = swords.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void showArmorList(List<Item> armors) {
        if (armors.isEmpty()) {
            System.out.println("У вас нет брони.");
            return;
        }
        System.out.println("Ваша броня:");
        for (int i = 0; i < armors.size(); i++) {
            Item p = armors.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void noEquip() { System.out.println("У вас нет снаряжения!");}
    public void showInventory(Hero hero) {System.out.println("Ваш инвентарь: " + hero.getInventory());}
    public void showInventoryWithName(Hero hero) {System.out.println(hero.getInventoryWithName());}
    public void zeroCount() { System.out.println("У вас нет этого предмета!"); }

}

