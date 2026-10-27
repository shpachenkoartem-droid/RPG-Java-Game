package com.game.service;

import com.game.enums.BattleResult;
import com.game.enums.ItemType;
import com.game.item.potions.Potion;
import com.game.model.*;
import com.game.model.enemies.Enemy;
import com.game.model.heroes.PlayableHeroes;
import com.game.model.Inventory;
import com.game.item.Item;


public class Battle {

    // Метод для проведения 1 раунда боя
    // Остальное возвращает в контроллер
    // Врага тут получаем уже готового по типу: огр, гоблин и другие
    public BattleResult fightRound(PlayableHeroes hero, Enemy enemy, int choice) {
        Inventory inventory = hero.getHeroInventory();
        Inventory potions = inventory.createOneTypeInventory(ItemType.POTION);


        switch (choice) {
                case 1 -> {
                    hero.attack(enemy);
                    ifKillEnemy(hero, enemy);
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

                    if (hero.getHp() <= 0) {
                        return BattleResult.LOSE;
                    }
                    return BattleResult.CONTINUE;
                }

                default -> throw new IllegalArgumentException("Неверный выбор действия");
            }
    }

    // Увеличивает деньги за победу над врагом
    public void getMoneyForEnemyKill(PlayableHeroes hero, Fighter enemy) {
        hero.incMoney(enemy.getMoney());
    }

    // Проверяет выпало ли что-то при убийстве врага, и если да - отдельный исход
    // Должны получать исход отсюда и публиковать сообщение выпало ли что-то или нет
    public BattleResult pickUpDrop(PlayableHeroes hero, Enemy enemy) {
        if (enemy.isDropped()) {
            hero.pickUp(enemy.getDrop());
            return BattleResult.WIN_WITH_DROP;
        }
        return BattleResult.WIN;
    }

    // Проверка на победу над врагом и переход к вопросу а выпало ли что-то
    public void ifKillEnemy(PlayableHeroes hero, Enemy enemy) {
        if (enemy.getHp() <= 0) {
            getMoneyForEnemyKill(hero, enemy);
            pickUpDrop(hero, enemy);
        }
    }
}

