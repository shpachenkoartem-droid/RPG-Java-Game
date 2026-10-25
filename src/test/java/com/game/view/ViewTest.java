package com.game.view;

import com.game.item.Equipment;
import com.game.item.chests.IronChest;
import org.junit.jupiter.api.Test;

class ViewTest {

    @Test
    void maimMenu() {
    }

    @Test
    void showHeroDnDDamage() {
    }

    @Test
    void showEquipmentDnDDamage() {
        Equipment equipment = new IronChest();
        System.out.print(equipment.getDamage());
    }
}