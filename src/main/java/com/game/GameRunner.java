package com.game;

import com.game.controller.Controller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println(">>> Запуск игровой логики из Spring Boot...");
        //gameController.run();
    }
}