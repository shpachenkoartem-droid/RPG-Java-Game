package main.java.com.game.model.Enemies;

import main.java.com.game.item.Item;
import main.java.com.game.model.Fighter;

import java.util.Random;


public abstract class Enemy extends Fighter {
    // Предмет, который выпадает
    private Item drop;
    // Шанс с которым предмет выпадает
    private int chanceDrop;
    // Статус - выпал ли
    private boolean isDropped;
    Random random = new Random();

    public Item getDrop() {
        return drop;
    }

    public int getChanceDrop() {
        return chanceDrop;
    }

    public boolean isDropped() {
        calculateIsDropped();
        return isDropped;
    }

    private void calculateIsDropped() {
        int rand = random.nextInt(1, 100);
        if (rand <= chanceDrop) {
            isDropped = true;
        } else {
            isDropped = false;
        }
    }

    public void setDrop(Item drop, int chanceDrop) {
        this.drop = drop;
        this.chanceDrop = chanceDrop;
    }
}
