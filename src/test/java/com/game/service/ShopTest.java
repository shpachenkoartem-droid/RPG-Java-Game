package com.game.service;

import com.game.enums.PurchaseResult;
import com.game.item.weapons.IronSword;
import com.game.model.heroes.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    private Hero hero;
    private Shop shop;
    private IronSword ironSword;

    @BeforeEach
    void setUp() {
        hero = new Hero();
        shop = new Shop();
        ironSword = new IronSword();
        // В магазин из основного кода кладём предмет, чтобы не создавать какой-то новый, а пользоваться старым
        shop.addShopItems(ironSword, 1);
    }

    @Test
    void buyIronSword() {
        PurchaseResult purchaseResult = shop.buySoloItem(hero, ironSword, 1);
        assertEquals(PurchaseResult.SUCCESS, purchaseResult);
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
}