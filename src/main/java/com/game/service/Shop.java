package main.java.com.game.service;

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

}
