package main.java.com.game.service;

import main.java.com.game.enums.PurchaseResult;
import main.java.com.game.item.*;
import main.java.com.game.model.*;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private final List<Item> shopItems = new ArrayList<>();
    public void addShopItems(Item item) {
        shopItems.add(item);
    }

    // Для всех предметов магазина
    public List<Item> getShopItems() {
        return shopItems;
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


    // Главная идея - создавать методы в Model, которые возвращают значения (можно enum),
    // а Controller эти значения обрабатывает и что-то выводит, он создаёт связь между выводимым из Model и из View

    // Сам процесс покупки когда уже выбрали предмет
    // Здесь он добавляется в инвентарь
    public PurchaseResult buySoloItem(PlayableHeroes hero, Item item) {
        if (hero.getMoney() > item.getCost()) {
            item.incCount();
            hero.decMoney(item.getCost());
            hero.pickUp(item);
            return PurchaseResult.SUCCESS;
        } else {
            return PurchaseResult.NOT_ENOUGH_MONEY;
        }
    }
}

    /*
    // Создание списка покупок и возможности выбора одного предмета
    public void buyItems(PlayableHeroes hero) {
        int choice_items =
        switch (choice_items) {
            case 1 -> buySoloItem(hero, getShopItem(0));
            case 2 -> buySoloItem(hero, getShopItem(1));
            case 3 -> buySoloItem(hero, getShopItem(2));
            case 4 -> buySoloItem(hero, getShopItem(3));
            case 5 -> buySoloItem(hero, getShopItem(4));
        }
    }
    */
