package main.java.com.game.view;

import main.java.com.game.item.Equipment;
import main.java.com.game.item.IronChest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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