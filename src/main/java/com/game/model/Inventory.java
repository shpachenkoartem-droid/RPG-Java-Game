package main.java.com.game.model;

import main.java.com.game.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items = new ArrayList<>();
    private int max_size = 20;

    public void addItem(Item item) {
        if (items.size() < max_size) {
            items.add(item);
        } else { System.out.println("Инвентарь забит, места нет!"); }

    }
    public void removeItem(Item item) {
        items.remove(item);
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

}
