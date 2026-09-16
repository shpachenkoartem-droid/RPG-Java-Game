package main.java.com.game.service;

import main.java.com.game.enums.BattleResult;
import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Potion;
import main.java.com.game.model.*;
import main.java.com.game.model.Inventory;
import main.java.com.game.item.Item;


public class Battle {


    // Метод для проведения 1 раунда боя
    // Остальное возвращает в контроллер
    public BattleResult fightRound(PlayableHeroes hero, Fighter enemy, int choice) {
        Inventory inventory = hero.getHeroInventory();
        Inventory potions = inventory.createOneTypeInventory(ItemType.POTION);


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
                    // Если зелья нет, то возвращаем NO_POTIONS и заканчиваем case 2
                    if (potions.isEmpty()) return BattleResult.NO_POTIONS;

                    Item chosen = potions.get(0);
                    System.out.println("Вот зелья: " + potions);
                    if (chosen instanceof Potion newPotion) {
                        hero.drinkPotion(newPotion);
                        hero.drop(newPotion);
                        enemy.attack(hero);
                        return BattleResult.DRINK_POTION;
                    }

                    if (hero.getHp() <= 0) return BattleResult.LOSE;
                    return BattleResult.CONTINUE;
                }

                default -> throw new IllegalArgumentException("Неверный выбор действия");
            }
    }

    public void getMoneyForEnemyKill(PlayableHeroes hero, Fighter enemy) {
        hero.incMoney(enemy.getMoney());
    }
}

