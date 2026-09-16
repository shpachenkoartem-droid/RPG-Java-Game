package main.java.com.game.model;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Equipment;
import main.java.com.game.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {
    private final List<Item> items = new ArrayList<>();
    private int max_size = 20;

    public Item get(int index) {
        return items.get(index);
    }

    public void addItem(Item item) {
        if (items.size() < max_size) {
            items.add(item);
        } else { System.out.println("Инвентарь забит, места нет!"); }

    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeItemByIndex(int index) {
        items.remove(index);
    }
    public List<Item> getItems() {
        return items;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getSize() {
        return items.size();
    }



    //Функция создания инвентаря с каким-то конкретным типом предметов. Возвращает массив предметов одного типа, которые есть в инвентаре
    public Inventory createOneTypeInventory(ItemType itemType) {

        // Прошли и добавили все предметы одного типа
        // Предметы одного типа свой класс сохранили - но компилятор об этом не знает

        Inventory OneTypeInventory = new Inventory();
        for (Item i : items) {
            // Проверяем типы и если совпадает, то подготавливаем чтобы положить в новый инвентарь
            if (Objects.equals(i.getItemType(), itemType)) {
                OneTypeInventory.addItem(i);
            }
        }

        // Проверяем добавленное, если есть одинаковое - выгоняем
        // j проходится по всему снаряжению, сравнивается с i
        // Выгоняем j если что-то совпадает
        int size = OneTypeInventory.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (Objects.equals(OneTypeInventory.get(i).getName(), OneTypeInventory.get(j).getName())) {
                    OneTypeInventory.removeItemByIndex(j);
                    size = OneTypeInventory.getSize();
                    j--;
                }
            }
        }

        // Получаем инвентарь уникальных предметов с 1 типом
        return OneTypeInventory;
    }
}
