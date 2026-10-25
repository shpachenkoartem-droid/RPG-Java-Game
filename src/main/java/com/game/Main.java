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