package main.java.com.game;

import main.java.com.game.controller.Controller;
import main.java.com.game.model.Heroes.Hero;
import main.java.com.game.service.Battle;
import main.java.com.game.service.Shop;
import main.java.com.game.view.View;

public class Main {
    public static void main (String[] args) {
        Hero hero = new Hero();
        Shop shop = new Shop();
        Battle battle1 = new Battle();
        View view = new View();


        Controller controller = new Controller(view, hero, shop, battle1);
        controller.run();

    }
}


// С 06.07.2026 (сикс сеееейвен) пытаюсь реализовать красивый выбор для игроков. Пока что вообще не представляю как это всё делать

// Попробовать использовать enum для локаций

// Изучить дальше:
// Коллекции - ArrayList, HashMap для инвентаря
//
//Исключения - вместо System.out.println выбрасывать исключения
//
//Композиция - вместо наследования для инвентаря
//
//Паттерны - Factory для создания врагов, Singleton для магазина
//
//Принципы SOLID - у вас уже есть зачатки, но можно углубиться

// Узнать можно ли сделать проверку isShop проще, а заодно посмотреть как вообще сделать все такие проверки проще и возможно ли это

// Цели - сделать битву против врага по выбору. мол инициализировать битву и указать только тех, кто сражается. после этого сделать торговца и дальнейший сюжет

// Задача на потом - сделать так что можно будет получать деньги не с помощью if (fighter2.getName() == "Гоблин") { fighter1.incMoney(10); }
// А с помощью fighter2.getForGoblin() В main.java.com.game.Fighter нельзя делать такой метод, ведь методы в main.java.com.game.Fighter должны подходить ко всем наследникам
// Это принцип подстановки Барбары Лисков (LSP)

// Вместе с торговцем сделать отдельные панельки для зелий и заклинаний с их стоимостью и эффектом
// Дать возможность использовать зелья герою между ходами