package main.java.com.game.controller;

import main.java.com.game.view.*;
import main.java.com.game.item.*;
import main.java.com.game.model.*;
import main.java.com.game.service.*;
import main.java.com.game.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        shop.initializeShop();

    }

    public void run() {
        while (true) {
            view.maimMenu();
            if (isIntInput()) {
                int choice_main = scanner.nextInt();
                if (choice_main == 0) {
                    break;
                }
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
    }


    public void chooseFighters() {
        view.showChooseFighters();
        if (isIntInput()) {
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
                default -> chooseFighters();
            }
        }
    }

    // isIntInput отвечает за то вводится ли число, default если вводится не то число
    public void chooseLocation() {
        view.showChooseLocation();
        if (isIntInput()) {
            int choice_location = scanner.nextInt();
            switch (choice_location) {
                case 1 -> hero.goShop();
                case 2 -> hero.goForest();
                default -> chooseLocation();
            }
        }
    }

    public boolean fullEquip() {
        view.showInventoryWithName(hero);
        if (hero.getInventory().isEmpty()) {
            view.noEquip();
            return false;
        }
        view.showChooseEquip();
        int choice_swap_equip = scanner.nextInt();
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

    // Проверяет ли является число целыми число ли вообще. Если не является - заставляет вводить заново
    // Если является - возвращает true
    private boolean isIntInput() {
        do {
            if (scanner.hasNextInt()) return true;
            else {
                System.out.println("\nЭто не число. Попробуйте снова.");
                scanner.next();
                return false;
            }
        } while (false);
    }

    private int readIntInRange(int min, int max) {
        while (true) {
            // hasNextInt проверка будет ли следующее число целым
            if (isIntInput()) {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    view.wrong();
                    System.out.println("Введите число от " + min + " до " + max);
                }
            }
        }
    }


    // Понять как сделать одинаковым sword и armor
    public void chooseEquipment(PlayableHeroes hero, ItemType itemType) {
        // Создаём инвентарь с мечами

        List<Item> equip = createOneTypeInventory(hero, itemType);


        // !!!
        view.showEquipmentList(equip, itemType);




        // Выбираем номер предмета из инвентаря
        int choice_equip_from_inventory = readIntInRange(1, equip.size());
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
        view.showEquipmentList(swords, ItemType.SWORD);

        // Выбираем номер предмета из инвентаря
        int choice_equip_from_inventory = readIntInRange(1, swords.size());

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
        view.showEquipmentList(armors, ItemType.ARMOR);

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
    // Сам процесс покупки когда уже выбрали предмет
    public void buySoloItem(PlayableHeroes hero, Item item) {
        if (hero.isShop()) {
            if (hero.getMoney() > item.getCost()) {
                item.incCount();
                hero.decMoney(item.getCost());
                hero.pickUp(item);
                view.successBuying();
            } else { view.notEnoughMoney(); }
        }
    }

    // Создание списка покупок и возможности выбора одного предмета
    public void buyItems(PlayableHeroes hero) {
        view.showMoney(hero);
        view.whatYouWantBuy();
        view.showShopStock(shop);
        if (isIntInput()) {
            int choice_items = scanner.nextInt();
            switch (choice_items) {
                case 1 -> buySoloItem(hero, shop.getShopItem(0));
                case 2 -> buySoloItem(hero, shop.getShopItem(1));
                case 3 -> buySoloItem(hero, shop.getShopItem(2));
                case 4 -> buySoloItem(hero, shop.getShopItem(3));
                case 5 -> buySoloItem(hero, shop.getShopItem(4));
            }
        }
    }
}

