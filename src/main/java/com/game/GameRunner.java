package com.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// При старте Spring создаёт экземпляр класса GameRunner
// И кладёт его в контейнер
@Component
public class GameRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println(">>> Запуск игровой логики из Spring Boot...");
        //gameController.run();
    }
}