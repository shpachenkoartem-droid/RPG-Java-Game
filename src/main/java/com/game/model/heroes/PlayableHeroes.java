package com.game.model.heroes;

import com.game.item.Item;
import com.game.enums.Locations;
import com.game.model.Fighter;
import com.game.model.Inventory;

import java.util.ArrayList;
import java.util.List;

// Дал возможность героям ходить в локации. Долго думал как сделать, но по итогу вышло достаточно просто.
public abstract class PlayableHeroes extends Fighter implements Healer {
    private int heal;
    private Locations location = Locations.SHOP;
    private final Inventory inventory = new Inventory();

    // Возвращает инвентарь как объект
    // Например, Inventory@3c130745
    public Inventory getHeroInventory() {
        return inventory;
    }

    // Возвращает инвентарь со списком имён
    // Например, [Железный меч, Золотой меч]
    public List<String> getInventoryWithName() {
        List<String> arrayWithNames = new ArrayList<>();
        for (Item i : inventory.getItems()) {
            arrayWithNames.add(i.getName());
        }
        return arrayWithNames;
    }

    public void pickUp(Item item) {
        inventory.addItem(item);
    }

    public void drop(Item item) {
        inventory.removeItem(item);
    }

    public Locations getLocation() {
        return location;
    }

    public boolean isForest() {
        if (getLocation() == Locations.FOREST) { return true; } else { return false; }
    }

    public boolean isShop() {
        if (getLocation() == Locations.SHOP) { return true; } else { return false; }
    }

    public boolean isCliff() {
        if (getLocation() == Locations.CLIFF) { return true;} else { return false; }
    }

    public void goForest() {
        location = Locations.FOREST;
    }

    public void goShop() {
        location = Locations.SHOP;
    }

    public void goCliff() {
        location = Locations.CLIFF;
    }

    @Override
    public void heal(Fighter target) {
        target.incHp(getHeal());
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        if (heal <= 0) { System.out.print("Лечение должно быть больше 0"); }
        else { this.heal = heal; }
    }
}
