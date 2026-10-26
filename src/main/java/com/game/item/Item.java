package com.game.item;

import com.game.enums.ItemType;

// Абстрактный метод для всех предметов вообще
public abstract class Item {
    private ItemType itemType;
    private ItemType subType;
    private String name;
    private int cost;
    private int count;
    private String description;

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

    public void decCount() {
        setCount(getCount() - 1);
    }

    public void increaseCountWithNumber(int number) {
        setCount(getCount() + number);
    }

    public boolean checkCountMoreZero() {
        if (getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean checkCountMoreZeroAndDec() {
        if (getCount() > 0) {
            decCount();
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

    public ItemType getSubType() {
        return subType;
    }

    public void setSubType(ItemType subType) {
        this.subType = subType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
