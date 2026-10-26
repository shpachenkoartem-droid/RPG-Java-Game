package com.game.controller;

import com.game.model.enemies.Enemy;
import com.game.model.enemies.Goblin;
import com.game.model.enemies.Ogre;
import com.game.model.heroes.Hero;
import com.game.model.heroes.PlayableHeroes;
import com.game.view.*;
import com.game.item.*;
import com.game.model.*;
import com.game.service.*;
import com.game.enums.*;

import java.util.List;

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
        view.introduction();
        while (true) {
            // Весь ввод в View, Controller соединяет ввод и реализацию Model
            int choiceMain = view.mainMenu();
            if (choiceMain == 0) break;

            switch (choiceMain) {
                case 1 -> fullChooseFightersForest();
                case 2 -> chooseLocation();
                case 3 -> buyItems(hero);
                case 4 -> fullEquip();
                case 5 -> view.showStats(hero);
                case 6 -> openInventory();
                default -> run();
            }
        }
    }

    // Выбор врага - кто он такой
    public void fullChooseFightersForest() {
        if (!hero.isForest()) {
            view.notInForest();
            view.heroCurrentLocation(hero);
            return;
        }
        int choice = view.showChooseFightersForest();
        Enemy enemy = switch (choice) {
            case 1 -> new Ogre();
            case 2 -> new Goblin();
            default -> null;
        };
        if (enemy != null) {
            battleControl(hero, enemy);
        }
    }

    public void fullChooseFightersCliff() {
        if (!hero.isCliff()) {
            view.notInCliff();
            view.heroCurrentLocation(hero);
            return;
        }
        int choice = view.showChooseFightersCliff();
        Enemy enemy = switch (choice) {
            case 1 -> new Ogre();
            case 2 -> new Goblin();
            default -> null;
        };
        if (enemy != null) {
            battleControl(hero, enemy);
        }
    }

    public void chooseLocation() {
        int choiceLocation = view.showChooseLocation();
        switch (choiceLocation) {
            case 1 -> {
                hero.goShop();
                view.inShop();
            }
            case 2 -> {
                hero.goForest();
                view.inForest();
            }
            default -> chooseLocation();
        }
    }

    public boolean fullEquip() {

        // Сам heroInventory это объект в памяти, его надо доставать разными методами из Inventory
        Inventory heroInventory = hero.getHeroInventory();

        if (heroInventory.isEmpty()) {
            view.noEquip();
            return false;
        }

        int choiceSwapEquip = view.showChooseEquip(hero);
        switch (choiceSwapEquip) {
            case 1 -> chooseOneTypeEquipment(hero, ItemType.SWORD);
            case 2 -> chooseOneTypeEquipment(hero, ItemType.ARMOR);
            default -> fullEquip();
        }
        return true;
    }


    public void chooseOneTypeEquipment(PlayableHeroes hero, ItemType itemType) {

        // Создаём временный инвентарь с предметами, переданными в ItemType
        Inventory equip = hero.getHeroInventory().createOneTypeInventory(itemType);
        if (equip.isEmpty()) {
            view.zeroCount();
            return;
        }
        // Выводим список предметов на экипировку
        view.showItemList(equip, itemType);

        // Выбираем номер предмета из инвентаря
        int choice = view.readIntInRange(1, equip.getSize());

        Item chosen = equip.get(choice - 1);

        // Проверяем является ли chosen типа Equipment или его наследника
        // Затем говорим что newEquip это Equipment и он равен chosen
        if (chosen instanceof Equipment newEquip) {
            // После того как выбрали что будем экипировать, выбрасываем это
            hero.drop(newEquip);
            hero.equip(newEquip);
            boolean decreased = newEquip.checkCountMoreZeroAndDec();
            if (!decreased) {
                view.zeroCount();
            }
        }
    }


    public void buyItems(PlayableHeroes hero) {
        if (!hero.isShop()) {
            view.notInShop();
            return;
        }
        List<Item> itemsInShop = shop.getShopItems();
        view.showMoney(hero);
        view.whatYouWantBuy();
        view.showShopStock(shop);
        int choice = view.readIntInRange(0, itemsInShop.size());
        if (choice == 0) return;

        Item selectedItem = itemsInShop.get(choice - 1);
        view.showItemData(selectedItem);
        int agree = view.showBuyMenu();

        // butResult получит один из четырёх состояний и передаст данные в Controller
        // А buySoloItem повзаимодействует с hero
        PurchaseResult buyResult = shop.buySoloItem(hero, selectedItem, agree);
        switch (buyResult) {
            case SUCCESS -> view.successBuying();
            case NOT_ENOUGH_MONEY -> view.notEnoughMoney();
            case DENIED -> view.deniedBuying();
            case NOT_IN_SHOP_STOCK -> view.notInShopStock();
        }
    }

    public void openInventory() {
        // Показывает инвентарь
        view.showInventoryWithName(hero);
        while (true) {
            int choiceInventory = view.showInventoryMenu(); // Вызываем меню и передаём число в него
            if (choiceInventory == 0) break;
            if (hero.getHeroInventory().getItems().isEmpty()) {
                view.noEquip();
                break;
            }
            view.showInventoryWithName(hero);
            // Запрашиваем новое число, которое определит номер выбранного предмета в инвентаре
            int choice = view.readIntInRange(1, hero.getHeroInventory().getSize());
            // Сам выбранный предмет
            Item chosen = hero.getHeroInventory().get(choice - 1);

            switch (choiceInventory) {
                case 1 -> chooseItemToDrop(chosen); // Перейти дальше к меню выбрасывания предметов
                case 2 -> view.showItemData(chosen); // Перейти к описанию предмета
                default -> openInventory();
            }
        }
    }

    public void chooseItemToDrop(Item chosen) {
        if (hero.getHeroInventory().getSize() == 0) {
            view.noEquip();
            return;
        }
        // Сам выбранный предмет
        hero.drop(chosen);
        view.showInventoryWithName(hero);
    }

    public void battleControl(PlayableHeroes hero, Enemy enemy) {
        view.showFightersHP(hero, enemy);
        // То что и было раньше - в Controller действия
        // В идеале контроллер должен только вызывать методы
        // Может получится сделать метод, который соединяет раунды в один
        // Method(param1, param2)
        while (hero.getHp() > 0 && enemy.getHp() > 0) {

            int choice = view.showFightActions();
            BattleResult result = battle1.fightRound(hero, enemy, choice);

            switch (result) {
                case ATTACK, CONTINUE -> view.showBothAttackResult(hero, enemy);
                case NO_POTIONS -> view.noPotions();
                case DRINK_POTION -> {
                    view.showDrinkPotion(hero);
                    view.showEnemyAttackResult(hero, enemy);
                }
                case WIN -> view.heroWinInFight();
                case WIN_WITH_DROP -> view.droppingOut(enemy);
                case LOSE -> view.heroLoseInFight();
            }
        }
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