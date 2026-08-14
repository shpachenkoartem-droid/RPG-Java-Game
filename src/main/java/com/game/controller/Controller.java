package main.java.com.game.controller;

import main.java.com.game.view.*;
import main.java.com.game.item.*;
import main.java.com.game.model.*;
import main.java.com.game.service.*;
import main.java.com.game.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    private final View view;
    private final Hero hero;
    private final Shop shop;
    private final Battle battle1;

    public Controller(View view, Hero hero, Shop shop, Battle battle1) {
        this.view = view;
        this.hero = hero;
        this.shop = shop;
        this.battle1 = battle1;
        shop.initializeShop();

    }

    public void run() {
        while (true) {
            // Весь ввод в view, Controller соединяет ввод и реализацию model
            int choice_main = view.maimMenu();
            if (choice_main == 0) break;

            switch (choice_main) {
                case 1 -> chooseFighters();
                case 2 -> chooseLocation();
                case 3 -> buyItems(hero);
                case 4 -> fullEquip();
                case 5 -> view.showStats(hero);
                default -> run();
            }
        }
    }


    public void chooseFighters() {
        int choice_create_fight = view.showChooseFighters();
        switch (choice_create_fight) {
            case 1 -> {
                Ogre ogre = new Ogre();
                battle1.battle(hero, ogre);
            }
            case 2 -> {
                Goblin goblin = new Goblin();
                battle1.battle(hero, goblin);
            }
            default -> chooseFighters();
        }
    }

    // isIntInput отвечает за то вводится ли число, default если вводится не то число
    public void chooseLocation() {
        int choice_location = view.showChooseLocation();
        switch (choice_location) {
            case 1 -> hero.goShop();
            case 2 -> hero.goForest();
            default -> chooseLocation();
        }
    }

    public boolean fullEquip() {
        view.showInventoryWithName(hero);
        if (hero.getInventory().isEmpty()) {
            view.noEquip();
            return false;
        }
        int choice_swap_equip = view.showChooseEquip();
        switch (choice_swap_equip) {
            case 1 -> chooseSwords(hero);
            case 2 -> chooseArmor(hero);
            default -> fullEquip();
        }
        return true;
    }

    //Функция создания инвентаря с каким-то конкретным типом предметов. Возвращает массив предметов одного типа, которые есть в инвентаре
    public List<Item> createOneTypeInventory(PlayableHeroes hero, ItemType itemType) {

        // Прошли и добавили все предметы одного типа
        ArrayList<Item> OneTypeInventory = new ArrayList<>();
        for (Item i : hero.getInventory()) {
            // Проверяем типы и если совпадает, то подготавливаем чтобы положить в новый инвентарь
            if (Objects.equals(i.getItemType(), itemType)) {
                OneTypeInventory.add(i);
                view.showCount(i);
            }
        }

        // Проверяем добавленное, если есть одинаковое - выгоняем
        // j проходится по всему снаряжению, сравнивается с i
        // Выгоняем j если что-то совпадает
        int size = OneTypeInventory.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (Objects.equals(OneTypeInventory.get(i).getName(), OneTypeInventory.get(j).getName())) {
                    OneTypeInventory.remove(j);
                    size = OneTypeInventory.size();
                    j = j - 1;
                }
            }
        }

        // Получаем инвентарь уникальных предметов с 1 типом
        return OneTypeInventory;
    }

    // Понять как сделать одинаковым sword и armor
    public void chooseEquipment(PlayableHeroes hero, ItemType itemType) {
        // Создаём инвентарь с мечами

        List<Item> equip = createOneTypeInventory(hero, itemType);


        // !!!
        view.showItemList(equip, itemType);




        // Выбираем номер предмета из инвентаря
        int choice_equip_from_inventory = view.readIntInRange(1, equip.size());
        Equipment newEquip = (Equipment) equip.get(choice_equip_from_inventory - 1);

        // После того как выбрали что будем экипировать, выбрасываем это
        hero.drop(newEquip);

        if (choice_equip_from_inventory <= equip.size()) {
            view.here();
        }
    }

    // Выбирает из массива со всем снаряжением мечи и создаёт массив с мечами
    public void chooseSwords(PlayableHeroes hero) {
        // Создаём инвентарь с мечами
        List<Item> swords = createOneTypeInventory(hero, ItemType.SWORD);
        view.showItemList(swords, ItemType.SWORD);

        // Выбираем номер предмета из инвентаря
        int choice_equip_from_inventory = view.readIntInRange(1, swords.size());

        Equipment newSword = (Equipment) swords.get(choice_equip_from_inventory - 1);

        // После того как выбрали что будем экипировать, выбрасываем это
        hero.drop(newSword);

        if (choice_equip_from_inventory <= swords.size()) {
            view.showDmg(hero);
            // Хорошая практика вызывать View только лишь в Controller
            hero.equipSword(newSword);
            boolean decreased = newSword.decCount();
            if (!decreased) {
                view.zeroCount();
            }
            view.showDmg(hero);
        }
    }

    public void chooseArmor(PlayableHeroes hero) {
        List<Item> armors = createOneTypeInventory(hero, ItemType.ARMOR);
        view.showItemList(armors, ItemType.ARMOR);

        int choice_equip_from_inventory = view.readIntInRange(1, armors.size());
        if (choice_equip_from_inventory <= armors.size()) {
            view.showReduceDamage(hero);
            Equipment newArmor = (Equipment) armors.get(choice_equip_from_inventory-1);
            hero.equipArmor(newArmor);
            view.showReduceDamage(hero);
        }
    }

    public Potion choosePotion(PlayableHeroes hero) {
        List<Item> potions = createOneTypeInventory(hero, ItemType.POTION);
        view.showItemList(potions, ItemType.POTION);

        int choice_equip_from_inventory = view.readIntInRange(1, potions.size());
        if (choice_equip_from_inventory <= potions.size()) {
            view.showHP(hero);
            hero.drinkPotion((Potion) potions.get(choice_equip_from_inventory-1));
            view.showHP(hero);
        }
        return null;
    }

    public void buyItems(PlayableHeroes hero) {
        List<Item> items = shop.getShopItems();
        view.showMoney(hero);
        view.whatYouWantBuy();
        view.showShopStock(shop);
        int choice = view.readIntInRange(1, items.size());
        if (choice == 0) return;
        Item selectedItem = items.get(choice - 1);
        // result получит один из трёх состояний и передаст данные в Controller
        // А buySoloItem повзаимодействует с hero
        PurchaseResult result = shop.buySoloItem(hero, selectedItem);
        view.showPurchaseResult(result);
    }
}

// Сделать Controller тоньше, лишь координатором. Всё что взаимодействует с model дать тем самым model
// Controller узнаёт данные из model, проводит небольшие операции с ним и передаёт их во view
/*
Model (чистая логика, без System.out/Scanner)
├── Hero, Goblin, Ogre и другие существа
├── Item, Equipment, Potion (состояние и поведение)
├── Inventory (коллекция предметов с методами фильтрации)
├── BattleEngine (чистая механика боя без ввода-вывода)
└── Shop (товары, логика покупки без подтверждения)

View (вся консольная работа)
├── ConsoleView – методы showMainMenu(), showBattleStatus(), requestAction(), showInventory...
        └── InputReader – обёртка над Scanner с методами getIntInRange(), getString() и т.д.
        (может быть частью View или отдельным утилитным классом)

Controller (тонкий, не содержит логики предметов/боя)
├── GameController – run(), startBattle(), handleShop(), manageEquipment()
        │   Использует InputReader для получения команд, View для отображения.
│   Вызывает методы Model (Hero, BattleEngine, Shop) и передаёт результаты во View.

 */