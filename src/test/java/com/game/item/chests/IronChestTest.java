package com.game.item.chests;

import com.game.enums.ItemType;
import com.game.model.heroes.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IronChestTest {
    private Hero hero;
    private IronChest ironChest;

    @BeforeEach
    void setUp() {
        hero = new Hero();
        ironChest = new IronChest();
    }

    @Test
    void isSword() {
        assertEquals(ItemType.ARMOR, ironChest.getItemType());
    }

    @Test
    void checkCountAfterCreation() {
        assertEquals(1, ironChest.getCount());
    }

    @Test
    void canBeEquipped() {
        hero.equip(ironChest);
        assertEquals(ironChest, hero.getArmor());
    }
}