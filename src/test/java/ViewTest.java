package test.java;

import main.java.com.game.item.Equipment;
import main.java.com.game.item.Chests.IronChest;
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