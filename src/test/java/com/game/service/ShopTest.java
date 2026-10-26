package com.game.service;

import com.game.enums.PurchaseResult;
import com.game.item.chests.IronChest;
import com.game.item.potions.HealPotion;
import com.game.item.potions.Poison;
import com.game.item.weapons.GoldSword;
import com.game.item.weapons.IronSword;
import com.game.model.heroes.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    private Hero hero;
    private Shop shop;

    private final IronSword ironSword = new IronSword();
    private final GoldSword goldSword = new GoldSword();
    private final IronChest ironChest = new IronChest();
    private final HealPotion healPotion = new HealPotion();
    private final Poison poison = new Poison();

    @BeforeEach
    void setUp() {
        hero = new Hero();
        shop = new Shop();
        shop.initializeShop();
        // Достать тут каждый предмет снаряжения, а не использовать новые
    }

    @Test
    void buyIronSword() {
        PurchaseResult purchaseResult = shop.buySoloItem(hero, ironSword, 1);
        assertEquals(PurchaseResult.NOT_IN_SHOP_STOCK, purchaseResult);
    }

    @Test
    void buyFiveIronSword() {
        shop.buySoloItem(hero,ironSword,1);
        shop.buySoloItem(hero,ironSword,1);
        shop.buySoloItem(hero,ironSword,1);
        shop.buySoloItem(hero,ironSword,1);
        PurchaseResult purchaseResult = shop.buySoloItem(hero, ironSword, 1);
        assertEquals(PurchaseResult.NOT_IN_SHOP_STOCK, purchaseResult);
    }

    @Test
    void buyWithOneGold() {
        hero.setMoney(1);
        PurchaseResult purchaseResult = shop.buySoloItem(hero, ironSword, 1);
        assertEquals(PurchaseResult.NOT_ENOUGH_MONEY, purchaseResult);
    }

    @Test
    void buyWithMinusOneGold() {
        hero.setMoney(-1);
        PurchaseResult purchaseResult = shop.buySoloItem(hero, ironSword, 1);
        assertEquals(PurchaseResult.NOT_ENOUGH_MONEY, purchaseResult);
    }


}