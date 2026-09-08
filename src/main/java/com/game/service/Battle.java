package main.java.com.game.service;

import java.util.List;
import main.java.com.game.enums.BattleResult;
import main.java.com.game.item.Item;
import main.java.com.game.model.*;
import main.java.com.game.model.Inventory;


public class Battle {
    /*
    public void battle(PlayableHeroes hero, Fighter enemy) {
        if (hero.isForest()) {
            System.out.println("Изначальное здоровье " + hero.getName() + ": " + hero.getHp());
            System.out.println("Изначальное здоровье " + enemy.getName() + ": " + enemy.getHp() + "\n");
            while (hero.getHp()>0 && enemy.getHp()>0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Выберите действие");
                System.out.println("1. Атаковать");
                System.out.println("2. Выпить зелье");
                int move = scanner.nextInt();
                switch (move) {
                    case 1 -> {
                        hero.attack(enemy);
                        enemy.attack(hero);
                    }

                    case 2 -> {
                        System.out.println(hero.getInventory());
                        // Надо сделать чтобы герой получал инвентарь, и по enum хранилище типов зелий. Затем зелья проверяются и если есть соответствующего типа, то пьют

                        // Отдельное хранилище для зелий
                        System.out.println("Здоровья до зелья: " + hero.getHp());
                        //choosePotion(hero);
                        System.out.println("Здоровья после зелья: " + hero.getHp());
                        enemy.attack(hero);
                    }
                }
            }


            // Результаты битвы
            if (enemy.getHp() == 0) { System.out.println(enemy.getName() + " побеждён!\n");}
            if (hero.getHp() == 0) { System.out.println(hero.getName() + " побеждён!\n");}

            // Получение денег
            hero.incMoney(enemy.getMoney());
        }
    }

     */


    public void getMoneyForEnemyKill(PlayableHeroes hero, Fighter enemy) {
        hero.incMoney(enemy.getMoney());
    }

    // Метод для проведения 1 раунда боя
    // Остальное возвращает в контроллер
    public BattleResult fightRound(PlayableHeroes hero, Fighter enemy, int choice) {
        Inventory inventory = hero.getInventory();
        Inventory equip = inventory.createOneTypeInventory(hero, itemType);

        switch (choice) {
                case 1 -> {
                    hero.attack(enemy);
                    if (enemy.getHp() <= 0) {
                        getMoneyForEnemyKill(hero, enemy);
                        return BattleResult.WIN;
                    }
                    enemy.attack(hero);
                    if (hero.getHp() <= 0) return BattleResult.LOSE;
                    return BattleResult.CONTINUE;
                }

                case 2 -> {

                    //hero.drinkPotion();
                    // Передать сюда зелья!!!
                    //if success
                    enemy.attack(hero);
                    hero.drinkPotion();
                    if (hero.getHp() <= 0) return BattleResult.LOSE;
                    return BattleResult.DRINK_POTION;
                }

                default -> throw new IllegalArgumentException("Неверный выбор действия");
            }
    }

    /*
    public void chooseFighters(int choice, PlayableHeroes hero) {

        switch (choice) {
            case 1 -> {
                Ogre ogre = new Ogre();
                battleCycle(hero, ogre);
            }
            case 2 -> {
                Goblin goblin = new Goblin();
                battleCycle(hero, goblin);
            }
            default -> chooseFighters(choice, hero);
        }
    }

     */
}

