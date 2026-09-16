package main.java.com.game.model;

import main.java.com.game.item.Item;
import main.java.com.game.enums.Locations;

import java.util.ArrayList;
import java.util.List;

// Дал возможность героям ходить в локации. Долго думал как сделать, но по итогу вышло достаточно просто. Надо на бумаге рисовать примерные схемки.
public abstract class PlayableHeroes extends Fighter implements Healer {
    private int heal;
    private Locations location = Locations.SHOP;
    private final Inventory inventory = new Inventory();

    public Inventory getHeroInventory() {
        return inventory;
    }

    // Просто легендарно. Сам всё создал и понял. И сработало идеально. Создаём отдельный массив только для вывода названий
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
        if (getLocation() == Locations.FOREST) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isShop() {
        if (getLocation() == Locations.SHOP) {
            return true;
        } else {
            return false;
        }
    }
    public void goForest() {
        location = Locations.FOREST;
    }

    public void goShop() {
        location = Locations.SHOP;
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
