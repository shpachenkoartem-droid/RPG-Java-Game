package main.java.com.game.tests;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Equipment;
import main.java.com.game.item.Item;
import main.java.com.game.model.Heroes.Hero;
import main.java.com.game.model.Inventory;
import main.java.com.game.view.View;
import org.junit.jupiter.api.Test;

class ControllerTest {

    @Test
    void run() {
    }

    @Test
    void fullEquip() {
    }

    @Test
    void chooseOneTypeEquipment() {
        Hero hero = new Hero();
        View view = new View();
        ItemType itemType = ItemType.SWORD;
        // Создаём временный инвентарь с предметами, переданными в ItemType
        Inventory equip = hero.getHeroInventory().createOneTypeInventory(itemType);
        if (equip.isEmpty()) {
            view.zeroCount();
            return;
        }
        // Выводим список предметов на экипировку
        view.showItemList(equip, itemType);

        // Выбираем номер предмета из инвентаря
        int choice = view.readIntInRange(1, equip.getSize());

        Item chosen = equip.get(choice-1);
        System.out.println(chosen);
        // Проверяем является ли chosen типа Equipment или его наследника
        // Затем говорим что newEquip это Equipment и он равен chosen
        if (chosen instanceof Equipment newEquip) {
            // После того как выбрали что будем экипировать, выбрасываем это
            hero.drop(newEquip);
            hero.equip(newEquip);
            boolean decreased = newEquip.decCount();
            if (!decreased) {
                view.zeroCount();
            }
        }
    }

    @Test
    void openInventory() {
    }

    @Test
    void chooseItemToDrop() {
    }
}