package main.java.com.game.service;

import main.java.com.game.enums.PurchaseResult;
import main.java.com.game.item.*;
import main.java.com.game.item.Chests.IronChest;
import main.java.com.game.item.Potions.HealPotion;
import main.java.com.game.item.Potions.Poison;
import main.java.com.game.item.Weapons.GoldSword;
import main.java.com.game.item.Weapons.IronSword;
import main.java.com.game.model.Heroes.PlayableHeroes;

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

    public void removeShopItem(Item item) {
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
        addShopItems(sword1, 1);
        addShopItems(sword2, 1);
        addShopItems(iron_chest, 1);
        addShopItems(healPotion, 3);
        addShopItems(poison, 3);
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
    public PurchaseResult buySoloItem(PlayableHeroes hero, Item item, int agree) {
        if (agree == 2) {
            return PurchaseResult.DENIED;
        }
        // Если у героя хватает денег и предмет есть в магазине
        if (hero.getMoney() > item.getCost() && item.checkCountMoreZero()) {
            // При покупке уменьшаем количество в магазине
                hero.decMoney(item.getCost());
                hero.pickUp(item);
                removeShopItem(item);
                return PurchaseResult.SUCCESS;
        } else {
            return PurchaseResult.NOT_ENOUGH_MONEY;
        }
    }
}