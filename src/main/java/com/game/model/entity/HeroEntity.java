package com.game.model.entity;

import com.game.coordinates.Coordinates;
import com.game.coordinates.CoordinatesEmbeddable;
import jakarta.persistence.*;


// Это JPA-сущность
// Набор правил, который описывает
// Как связать Java-объекты с таблицами базы данных
@Entity // Пометили класс как таблицу в БД
@Table(name = "heroes") // Кладём его в общую таблицу для всех героев - heroes
public class HeroEntity {
    @Id // Создали id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Присвоили id какое-то значение
    private Long id;
    private String name;
    private int maxHp;
    private int hp;
    private int money;
    private int heal;

    // Встраиваю координаты в эту таблицу в БД
    @Embedded
    private CoordinatesEmbeddable coordinatesEmb;

    // Нужно передать конструктор без аргументов чтобы Hibernate создал экземпляр класса
    public HeroEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public CoordinatesEmbeddable getCoordinatesEmb() {
        return coordinatesEmb;
    }

    public void setCoordinatesEmb(CoordinatesEmbeddable coordinatesEmb) {
        this.coordinatesEmb = coordinatesEmb;
    }
}