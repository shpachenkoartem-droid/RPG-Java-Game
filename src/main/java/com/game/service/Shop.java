package main.java.com.game.service;

import main.java.com.game.item.*;
import main.java.com.game.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    private final List<Item> shopItems = new ArrayList<>();
    public void addShopItems(Item item) {
        shopItems.add(item);
    }

    // Для всех предметов магазина
    public List<Item> getShopItems() {
        return shopItems;
    }

    // Для одного предмета магазина
    public Item getShopItem(int number) {
        return shopItems.get(number);
    }

    public int getShopSize() {
        return shopItems.size();
    }

    public void initializeShop() {
        addShopItems(sword1);
        addShopItems(sword2);
        addShopItems(iron_chest);
        addShopItems(healPotion);
        addShopItems(poison);
    }
    // Наполняем магазин товарами
    private final IronSword sword1 = new IronSword();
    private final GoldSword sword2 = new GoldSword();
    private final IronChest iron_chest = new IronChest();
    private final HealPotion healPotion = new HealPotion();
    private final Poison poison = new Poison();

    // Сам процесс покупки когда уже выбрали предмет
    public void buySoloItem(PlayableHeroes hero, Item item) {
        if (hero.isShop()) {
            if (hero.getMoney() > item.getCost()) {
                item.incCount();
                hero.decMoney(item.getCost());
                hero.pickUp(item);
                System.out.println("\nДзынь! Успешная покупка!");
            } else { System.out.println("Не хватает денег!"); }
        }
    }

    // Создание списка покупок и возможности выбора одного предмета
    public void buyItems(PlayableHeroes hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВаши монеты: " + hero.getMoney());
        // Если в магазине, хз можно ли сделать проверку проще, вроде и так хорошо
        System.out.println("Выберите что хотите купить");
        for (int i = 0; i < getShopSize(); i++) {
            // Этапы обращения: магазин - всё снаряжение магазина - конкретный предмет - его стоимость
            System.out.println(i+1 + "." + "Купить " + getShopItems().get(i).getName() + " за " + getShopItems().get(i).getCost());
        }
        int choice_items = scanner.nextInt();
        switch (choice_items) {
            case 1 -> buySoloItem(hero, getShopItem(0));
            case 2 -> buySoloItem(hero, getShopItem(1));
            case 3 -> buySoloItem(hero, getShopItem(2));
            case 4 -> buySoloItem(hero, getShopItem(3));
            case 5 -> buySoloItem(hero, getShopItem(4));
        }
    }
}
