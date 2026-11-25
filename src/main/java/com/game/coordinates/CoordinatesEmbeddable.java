package com.game.coordinates;

import jakarta.persistence.Embeddable;

// Сделал coordinates такие, чтобы их можно было встроить в другую таблицу БД
@Embeddable
public class CoordinatesEmbeddable {
    private int x;
    private int y;

    public CoordinatesEmbeddable() {}

    public CoordinatesEmbeddable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}