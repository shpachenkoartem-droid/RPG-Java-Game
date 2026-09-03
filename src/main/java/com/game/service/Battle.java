package main.java.com.game.service;

import java.util.Objects;
import java.util.Scanner;

import main.java.com.game.enums.BattleResult;
import main.java.com.game.model.*;


public class Battle {
    public void battle(PlayableHeroes hero, Fighter fighter2) {
        if (hero.isForest()) {
            System.out.println("Изначальное здоровье " + hero.getName() + ": " + hero.getHp());
            System.out.println("Изначальное здоровье " + fighter2.getName() + ": " + fighter2.getHp() + "\n");
            while (hero.getHp()>0 && fighter2.getHp()>0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Выберите действие");
                System.out.println("1. Атаковать");
                System.out.println("2. Выпить зелье");
                int move = scanner.nextInt();
                switch (move) {
                    case 1 -> {
                        hero.attack(fighter2);
                        fighter2.attack(hero);
                    }

                    case 2 -> {
                        System.out.println(hero.getInventory());
                        // Надо сделать чтобы герой получал инвентарь, и по enum хранилище типов зелий. Затем зелья проверяются и если есть соответствующего типа, то пьют

                        // Отдельное хранилище для зелий
                        System.out.println("Здоровья до зелья: " + hero.getHp());
                        //choosePotion(hero);
                        System.out.println("Здоровья после зелья: " + hero.getHp());
                        fighter2.attack(hero);
                    }
                }
            }


            // Результаты битвы
            if (fighter2.getHp() == 0) { System.out.println(fighter2.getName() + " побеждён!\n");}
            if (hero.getHp() == 0) { System.out.println(hero.getName() + " побеждён!\n");}

            // Получение денег
            hero.incMoney(fighter2.getMoney());
        }
    }

    public void getMoneyForEnemyKill(Hero hero, Fighter fighter) {
        hero.incMoney(fighter.getMoney());
    }

    // Метод для проведения 1 раунда боя
    // Остальное возвращает в контроллер
    public BattleResult fightRound(PlayableHeroes hero, Fighter fighter, int choice) {
        // Мы конечно сделали тут while, но всё равно сразу же возвращаем результат в контроллер
            switch (choice) {
                case 1 -> {
                    hero.attack(fighter);
                    fighter.attack(hero);
                    return BattleResult.ATTACK;
                }

                case 2 -> {
                    fighter.attack(hero);
                    return BattleResult.DRINK_POTION;
                }
            }
        if (Objects.equals(fighter.getHp(), 0))  return BattleResult.WIN;
        if (Objects.equals(hero.getHp(), 0))  return BattleResult.LOSE;
    return BattleResult.NONE;
    }
}

