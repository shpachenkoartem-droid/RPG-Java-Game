package com.game.model;

import com.game.item.Item;
import com.game.item.weapons.GoldSword;
import com.game.item.weapons.IronSword;
import com.game.model.heroes.Hero;
import com.game.model.heroes.PlayableHeroes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventory;
    private IronSword ironSword;
    private GoldSword goldSword;
    private Hero hero;
    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        ironSword = new IronSword();
        goldSword = new GoldSword();
        hero = new Hero();
    }

    /*@Test
    void addItems() {
        hero.getHeroInventory().addItem(ironSword);
        hero.getHeroInventory().addItem(goldSword);
        List<Item> test = new ArrayList<>();
        test.add(ironSword);
        test.add(goldSword);
        assertEquals(1, hero.getInventoryWithName());
    }*/

    /*@Test
    void getItems() {
        List<Item> test = inventory.getItems();
        test.add(ironSword);
        test.add(goldSword);
        assertEquals(1, test);
    }*/

}