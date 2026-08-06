package main.java.com.game.item;

import main.java.com.game.enums.ItemType;

// Абстрактный метод для всех предметов вообще
public abstract class Item {
    private ItemType itemType;
    private String name;
    private int cost;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incCount() {
        setCount(getCount() + 1);
    }

    public boolean decCount() {
        if (getCount() > 0) {
            setCount(getCount() - 1);
            return true;
        }
    return false;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public boolean isNotNull() {
        return getCount() > 0;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
