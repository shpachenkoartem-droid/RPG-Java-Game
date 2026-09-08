package main.java.com.game.controller;

import main.java.com.game.view.*;
import main.java.com.game.item.*;
import main.java.com.game.model.*;
import main.java.com.game.service.*;
import main.java.com.game.enums.*;

import java.util.List;

public class Controller {
    private final View view;
    private final Hero hero;
    private final Shop shop;
    private final Inventory inventory;
    private final Battle battle1;


    public Controller(View view, Hero hero, Shop shop, Battle battle1, Inventory inventory) {
        this.view = view;
        this.hero = hero;
        this.shop = shop;
        this.inventory = inventory;
        this.battle1 = battle1;
        shop.initializeShop();
    }

    public void run() {
        while (true) {
            // Весь ввод в View, Controller соединяет ввод и реализацию Model
            int choice_main = view.maimMenu();
            if (choice_main == 0) break;

            switch (choice_main) {
                case 1 -> fullChooseFighters();
                case 2 -> chooseLocation();
                case 3 -> buyItems(hero);
                case 4 -> fullEquip();
                case 5 -> view.showStats(hero);
                default -> run();
            }
        }
    }

    public void fullChooseFighters() {
        if (!hero.isForest()) {
            view.notInForest();
            view.heroCurrentLocation(hero);
            return;
        }
        int choice = view.showChooseFighters();
        Fighter enemy = switch (choice) {
            case 1 -> new Ogre();
            case 2 -> new Goblin();
            default -> null;
        };
        if (enemy != null) {
            battleControl(hero, enemy);
        }
    }


    public void chooseLocation() {
        int choice_location = view.showChooseLocation();
        switch (choice_location) {
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
        view.showInventoryWithName(hero);
        if (hero.getInventory().isEmpty()) {
            view.noEquip();
            return false;
        }
        int choice_swap_equip = view.showChooseEquip();
        switch (choice_swap_equip) {
            case 1 -> chooseOneTypeEquipment(hero, ItemType.SWORD);
            case 2 -> chooseOneTypeEquipment(hero, ItemType.ARMOR);
            default -> fullEquip();
        }
        return true;
    }


    public void chooseOneTypeEquipment(PlayableHeroes hero, ItemType itemType) {

        // Создаём временный инвентарь с предметами, переданными в ItemType
        List<Item> equip = inventory.createOneTypeInventory(hero, itemType);
        if (equip.isEmpty()) {
            view.zeroCount();
            return;
        }
        // Выводим список предметов на экипировку
        view.showItemList(equip, itemType);

        // Выбираем номер предмета из инвентаря
        int choice_equip_from_inventory = view.readIntInRange(1, equip.size());
        Equipment newEquip = (Equipment) equip.get(choice_equip_from_inventory - 1);

        // После того как выбрали что будем экипировать, выбрасываем это
        hero.drop(newEquip);

        // До сюда всё работает
        if (choice_equip_from_inventory <= equip.size()) {
            hero.equip(newEquip);
            boolean decreased = newEquip.decCount();
            if (!decreased) {
                view.zeroCount();
            }
        }
    }


    public void buyItems(PlayableHeroes hero) {
        if (hero.isShop()) {
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
        } else view.notInShop();
    }

    // Проведение атаки
    //int damage = hero.attack(enemy);
    //view.showAttackResult(hero, enemy, damage);

    public void battleControl(PlayableHeroes hero, Fighter enemy) {
        view.showFightersHP(hero, enemy);
        // То что и было раньше - в Controller действия
        // В идеале контроллер должен только вызывать методы
        // Может получится сделать метод, который соединяет раунды в один
        // Method(param1, param2)
        while (hero.getHp()>0 && enemy.getHp()>0) {

                int choice = view.showFightActions();
                BattleResult result = battle1.fightRound(hero, enemy, choice);

                switch (result) {
                    case ATTACK, CONTINUE -> view.showAttackResult(hero, enemy);
                    case DRINK_POTION -> view.showDrinkPotion(hero);
                    case WIN -> view.heroWinInFight();
                    case LOSE -> view.heroLoseInFight();
                }
            /*
                if (Objects.equals(result, BattleResult.ATTACK)) {
                    view.showAttackResult(hero, enemy);
                }

                if (Objects.equals(result, BattleResult.DRINK_POTION)) {
                    view.showDrinkPotionMenu(hero);
                }

             */
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