package main.java.com.game.controller;

import main.java.com.game.*;
import main.java.com.game.view.*;
import main.java.com.game.item.*;
import main.java.com.game.model.*;
import main.java.com.game.service.*;
import main.java.com.game.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    private final View view;
    private final Hero hero;
    private final Shop shop;
    private final Battle battle1;

    public Controller(View view, Hero hero, Shop shop, Battle battle1) {
        this.view = view;
        this.hero = hero;
        this.shop = shop;
        this.battle1 = battle1;

    }

    public void run() {
        shop.initializeShop();
        while (true) {
            view.maimMenu();
            int choice_main = scanner.nextInt();
            if (choice_main == 0) { break; }
            switch (choice_main) {
                case 1 -> chooseFighters();
                case 2-> chooseLocation();
                case 3 -> shop.buyItems(hero);
                case 4 -> fullEquip();
                case 5 -> view.showStats(hero);
            }
        }
    }
    public void chooseFighters() {
        view.showChooseFighters();
        int choice_create_fight = scanner.nextInt();
        switch (choice_create_fight) {
            case 1 -> {
                Ogre ogre = new Ogre();
                battle1.battle(hero, ogre);
            }
            case 2 -> {
                Goblin goblin = new Goblin();
                battle1.battle(hero, goblin);
            }
        }
    }
    public void chooseLocation() {
        view.showChooseLocation();
        int choice_location = scanner.nextInt();
        switch (choice_location) {
            case 1 -> hero.goShop();
            case 2 -> hero.goForest();
        }
    }

    public boolean fullEquip() {
        view.showInventaryWithName(hero);
        if (hero.getInventory().isEmpty()) {
            view.noEquip();
            // Придумать что сделать вместо break
            // break;
            return false;
        }
        view.showChooseEquip();
        int choice_swap_equip = scanner.nextInt();
        switch (choice_swap_equip) {
            case 1 -> chooseSwords(hero);
            case 2 -> chooseArmor(hero);
        }
        return true;
    }
    //Функция создания инвентаря с каким-то конкретным типом предметов. Возвращает массив предметов одного типа, которые есть в инвентаре
    public List<Item> createOneTypeInventory(PlayableHeroes hero, ItemType itemType) {
        ArrayList<Item> OneTypeInventory = new ArrayList<>();
        for (Item i: hero.getInventory()) {
            if (i.getItemType().equals(itemType)) {
                OneTypeInventory.add(i);
            }
        }

        return OneTypeInventory;
    }

    // Выбирает из массива со всем снаряжением мечи и создаёт массив с мечами
    public void chooseSwords(PlayableHeroes hero) {
        // Создаём инвентарь с мечами
        List<Item> swords = createOneTypeInventory(hero, ItemType.SWORD);
        view.showSwordList(swords);

        int choice_equip_from_inventory = scanner.nextInt();
        if (choice_equip_from_inventory <= swords.size()) {
            view.showDmg(hero);
            Equipment newSword = (Equipment) swords.get(choice_equip_from_inventory-1);
            hero.equipSword(newSword);
            newSword.decCount();
            view.showDmg(hero);
        }
    }
    public void chooseArmor(PlayableHeroes hero) {
        List<Item> armors = createOneTypeInventory(hero, ItemType.ARMOR);
        view.showArmorList(armors);

        int choice_equip_from_inventory = scanner.nextInt();
        if (choice_equip_from_inventory <= armors.size()) {
            view.showReduceDamage(hero);
            Equipment newArmor = (Equipment) armors.get(choice_equip_from_inventory-1);
            hero.equipArmor(newArmor);
            view.showReduceDamage(hero);
        }
    }

    public Potion choosePotion(PlayableHeroes hero) {
        List<Item> potions = createOneTypeInventory(hero, ItemType.POTION);
        view.showPotionList(potions);

        int choice_equip_from_inventory = scanner.nextInt();
        if (choice_equip_from_inventory <= potions.size()) {
            view.showHP(hero);
            hero.drinkPotion((Potion) potions.get(choice_equip_from_inventory-1));
            view.showHP(hero);
        }
        return null;
    }
}

