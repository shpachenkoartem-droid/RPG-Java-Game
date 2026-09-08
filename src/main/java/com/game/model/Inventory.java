package main.java.com.game.model;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Item;
import main.java.com.game.item.Potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {
    private final List<Item> inventory = new ArrayList<>();
    private int max_size = 20;

    public void addItem(Item item) {
        if (inventory.size() < max_size) {
            inventory.add(item);
        } else { System.out.println("Инвентарь забит, места нет!"); }

    }
    public void removeItem(Item item) {
        inventory.remove(item);
    }
    public List<Item> getInventory() {
        return inventory;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }

    /*
    public Potion getFirstPotion() {

    }

     */

    //Функция создания инвентаря с каким-то конкретным типом предметов. Возвращает массив предметов одного типа, которые есть в инвентаре
    public List<Item> createOneTypeInventory(PlayableHeroes hero, ItemType itemType) {

        // Прошли и добавили все предметы одного типа
        ArrayList<Item> OneTypeInventory = new ArrayList<>();
        for (Item i : hero.getInventory()) {
            // Проверяем типы и если совпадает, то подготавливаем чтобы положить в новый инвентарь
            if (Objects.equals(i.getItemType(), itemType)) {
                OneTypeInventory.add(i);
                //view.showCount(i);
            }
        }

        // Проверяем добавленное, если есть одинаковое - выгоняем
        // j проходится по всему снаряжению, сравнивается с i
        // Выгоняем j если что-то совпадает
        int size = OneTypeInventory.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (Objects.equals(OneTypeInventory.get(i).getName(), OneTypeInventory.get(j).getName())) {
                    OneTypeInventory.remove(j);
                    size = OneTypeInventory.size();
                    j = j - 1;
                }
            }
        }

        // Получаем инвентарь уникальных предметов с 1 типом
        return OneTypeInventory;
    }
}
