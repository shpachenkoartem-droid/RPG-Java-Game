package com.game;

import com.game.controller.Controller;
import com.game.model.heroes.Hero;
import com.game.service.Battle;
import com.game.service.Shop;
import com.game.view.View;

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