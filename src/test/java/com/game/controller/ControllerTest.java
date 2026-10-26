package com.game.controller;

import com.game.enums.ItemType;
import com.game.item.Equipment;
import com.game.item.Item;
import com.game.model.heroes.Hero;
import com.game.model.Inventory;
import com.game.view.View;
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
            boolean decreased = newEquip.checkCountMoreZeroAndDec();
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