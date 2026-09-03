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
                battleControl(hero, ogre);
            }
            case 2 -> {
                Goblin goblin = new Goblin();
                battleControl(hero, goblin);
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
            case 1 -> chooseEquipment(hero, ItemType.SWORD);
            case 2 -> chooseEquipment(hero, ItemType.ARMOR);
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

    public void chooseEquipment(PlayableHeroes hero, ItemType itemType) {

        // Создаём инвентарь с предметами, переданными в ItemType
        List<Item> equip = createOneTypeInventory(hero, itemType);
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

    // Проведение атаки
    //int damage = hero.attack(enemy);
    //view.showAttackResult(hero, enemy, damage);

    public void battleControl(PlayableHeroes hero, Fighter fighter) {
        // То что и было раньше - в Controller действия
        // В идеале контроллер должен только вызывать методы
        // Может получится сделать метод, который соединяет раунды в один
        while (hero.getHp()>0 && fighter.getHp()>0) {
            if (hero.isForest()) {
                view.showFightersHP(hero, fighter);
                //view.showFightActions();
                int choice = view.showFightActions();
                BattleResult result = battle1.fightRound(hero, fighter, choice);

                if (Objects.equals(result, BattleResult.ATTACK)) {
                    view.showAttackResult(hero, fighter);
                }

                if (Objects.equals(result, BattleResult.DRINK_POTION)) {
                    view.showDrinkPotionMenu(hero);
                }
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