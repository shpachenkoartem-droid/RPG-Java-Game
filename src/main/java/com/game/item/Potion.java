package main.java.com.game.item;

import main.java.com.game.model.Fighter;

public abstract class Potion extends Item {
    // Абстрактный метод - каждое зелье реализуется по-своему
    // Эта реализация должна быть у каждого. Неплохая вещь чтобы избежать дублирования кода
    public abstract void apply (Fighter target);

    // Здесь drink это внутренняя функция, если захотим чтобы существо пило зелье, то оно будет hero.drinkPotion
    // Создаём возможность пить зелья
    public boolean drink() {
        if (isNotNull()) {
            setCount(getCount() - 1);
            return true;
        } else {
            return false;
        }
    }

}

