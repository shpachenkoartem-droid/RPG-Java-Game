package com.game.service;

import com.game.enums.PurchaseResult;
import com.game.item.*;
import com.game.item.chests.IronChest;
import com.game.item.potions.HealPotion;
import com.game.item.potions.Poison;
import com.game.item.weapons.GoldSword;
import com.game.item.weapons.IronSword;
import com.game.model.heroes.PlayableHeroes;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private final List<Item> shopItems = new ArrayList<>();

    public void addShopItems(Item item, int number) {
        // Добавляем предмет в магазин
        shopItems.add(item);
        // Увеличиваем его число на 1 при добавлении
        item.increaseCountWithNumber(number);
    }

    // Использую перегрузку метода
    public void addShopItems(Item item) {
        addShopItems(item, 0);
    }

    public void removeShopItem(Item item) {
        item.decCount();
        if (item.getCount() == 0) {
            shopItems.remove(item);
        }
    }

    // Для всех предметов магазина
    public List<Item> getShopItems() {
        return shopItems;
    }

    public int getShopSize() {
        return shopItems.size();
    }

    public void initializeShop() {
        addShopItems(ironSword);
        addShopItems(goldSword);
        addShopItems(ironChest);
        addShopItems(healPotion, 2);
        addShopItems(poison, 2);
    }

    // Наполняем магазин товарами
    private final IronSword ironSword = new IronSword();
    private final GoldSword goldSword = new GoldSword();
    private final IronChest ironChest = new IronChest();
    private final HealPotion healPotion = new HealPotion();
    private final Poison poison = new Poison();

    // Главная идея - создавать методы в Model, которые возвращают значения (можно enum),
    // а Controller эти значения обрабатывает и что-то выводит, он создаёт связь между выводимым из Model и из View

    // Сам процесс покупки когда уже выбрали предмет
    // Здесь он добавляется в инвентарь
    public PurchaseResult buySoloItem(PlayableHeroes hero, Item item, int agree) {
        if (agree == 2) {
            return PurchaseResult.DENIED;
        }

        // Если у героя не хватает денег
        if (hero.getMoney() < item.getCost()) {
            return PurchaseResult.NOT_ENOUGH_MONEY;
        }

        // Если предмета нет в магазине
        if (!item.checkCountMoreZero()) {
            return PurchaseResult.NOT_IN_SHOP_STOCK;
        }

        // Если прошёл все проверки и готов закупиться
        hero.decMoney(item.getCost());
        hero.pickUp(item);

        // Убираем 1 предмет, и, если предметов не осталось, убираем строчку в магазине
        removeShopItem(item);
        return PurchaseResult.SUCCESS;
    }
}