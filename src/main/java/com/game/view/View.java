package main.java.com.game.view;

import main.java.com.game.enums.ItemType;
import main.java.com.game.enums.PurchaseResult;
import main.java.com.game.item.*;
import main.java.com.game.item.Potions.HealPotion;
import main.java.com.game.item.Potions.Poison;
import main.java.com.game.item.Potions.Potion;
import main.java.com.game.model.*;
import main.java.com.game.model.Enemies.Enemy;
import main.java.com.game.model.Heroes.PlayableHeroes;
import main.java.com.game.service.Shop;

import java.util.Objects;
import java.util.Scanner;


// Начинаю реализацию MVC - Model main.java.com.game.Controller main.java.com.game.View
// Класс main.java.com.game.View отвечает за все выводимые данные, то есть каждые print будет тут
// Класс Model не будет знать как выводить, он лишь будет передавать данные
// Все вызовы будет производить main.java.com.game.Controller, Model лишь будет выполнять техническую часть
public class View {
    private final Scanner scanner = new Scanner(System.in);
    public int maimMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Создать битву");
        System.out.println("2. Сменить локацию");
        System.out.println("3. Закупиться в магазине");
        System.out.println("4. Экипировать снаряжение");
        System.out.println("5. Вывести информацию о герое");
        System.out.println("6. Посмотреть инвентарь");
        System.out.println("0. Выйти");
        return readIntInRange(0,6);
    }

    public void Introduction() {System.out.println("Приветствую, герои! Ваше великое приключение начинается здесь. \nПройдите путь от драки на кулаках до оружия рассекающего небеса и получите звезду!");}


    // Локации


    public void inShop() {System.out.println("\nВы в магазине. Мирное место, где можно закупиться.");}
    public void inForest() {System.out.println("\nВы в лесу. Место, в котором живут мифические создания.");}
    public void inCliff() {System.out.println("\nВы в скалах. Обрывистое место, где существа не брезгуют вас уронить.");}
    public void notInShop() {System.out.println("\nВы должны быть в магазине");}
    public void notInForest() {System.out.println("\nВы должны быть в лесу");}
    public void notInCliff() {System.out.println("\nВы должны быть в скалах");}
    public void heroCurrentLocation(PlayableHeroes hero) {System.out.println("\nВы сейчас в локации: " + hero.getLocation());}
    public int showChooseLocation() {
        System.out.println("\n1. Магазин");
        System.out.println("2. Лес");
        return readIntInRange(1,2);
    }


    // Статы


    public void showStats(PlayableHeroes hero) {
        System.out.println("\n1. Имя: " + hero.getName());
        System.out.println("2. HP: " + hero.getHp());
        System.out.println("3. Max_HP: " + hero.getMax_hp());
        System.out.print("4. Урон: ");
        showHeroDnDDamage(hero);
        System.out.println("5. Деньги: " + hero.getMoney());
        System.out.println("6. Оружие: " + hero.getSword().getName());
        System.out.println("7. Броня: " + hero.getArmor().getName());
        System.out.println("8. Сопротивление урону: " + hero.getArmor().getArmorCount() + "\n");
    }

    public void showFighterName(Fighter fighter) {System.out.println("\nИмя: " + fighter.getHp());}
    public void showHP(Fighter fighter) {System.out.println("\nHP: " + fighter.getHp());}
    public void showMaxHP(Fighter fighter) {System.out.println("\nMax_HP: " + fighter.getMax_hp());}
    public void showDmg(Fighter fighter) {System.out.println("\nУрон: " + fighter.getSword().getDamage());}
    public void showMoney(Fighter fighter) {System.out.println("\nДеньги: " + fighter.getMoney());}
    public void showSword(Fighter fighter) {System.out.println("\nМеч: " + fighter.getSword());}
    public void showArmor(Fighter fighter) {System.out.println("\nБроня: " + fighter.getArmor());}
    public void showReduceDamage(Fighter  fighter) {System.out.println("\nСнижение урона: " + fighter.getReduceDamage());}


    // Битва

    public void heroWinInFight() {System.out.println("\nВы победили в бою!");}
    public void heroLoseInFight() {System.out.println("\nВы проиграли в бою!");}
    public void droppingOut(Enemy enemy) {System.out.println("\nВам выпал " + enemy.getDrop().getName());}

    public void showBothAttackResult(PlayableHeroes hero, Fighter target) {
        System.out.println("\n" + hero.getName() + " Получает " + target.getLastDamage() + " урона!");
        System.out.println("Здоровье " + hero.getName() + ": " + hero.getHp());
        System.out.println("\n" + target.getName() + " Получает " + hero.getLastDamage() + " урона!");
        System.out.println("Здоровье " + target.getName() + ": " + target.getHp());
    }

    public void showHeroAttackResult(PlayableHeroes hero, Fighter target) {
        System.out.println("\n" + target.getName() + " Получает " + hero.getLastDamage() + " урона!");
        System.out.println("Здоровье " + target.getName() + ": " + target.getHp());
    }

    public void showEnemyAttackResult(PlayableHeroes hero, Fighter target) {
        System.out.println("\n" + hero.getName() + " Получает " + target.getLastDamage() + " урона!");
        System.out.println("Здоровье " + hero.getName() + ": " + hero.getHp());
    }

    public void NoPotions() {System.out.println("\nУ вас нет зелий!");}

    public void showFightersHP(PlayableHeroes hero, Fighter fighter) {
        System.out.println("\nИзначальное здоровье " + hero.getName() + ": " + hero.getHp());
        System.out.println("Изначальное здоровье " + fighter.getName() + ": " + fighter.getHp() + "\n");
    }

    public int showFightActions() {
        System.out.println("\nВыберите действие: ");
        System.out.println("1. Атаковать");
        System.out.println("2. Выпить зелье");
        return readIntInRange(1,2);
    }

    public void showDrinkPotion(PlayableHeroes hero) {
        // Надо сделать чтобы герой получал инвентарь, и по enum хранилище типов зелий. Затем зелья проверяются и если есть соответствующего типа, то пьют
        System.out.println("Здоровья после зелья: " + hero.getHp());
    }

    public int showChooseFightersForest() {
        System.out.println("\nВыберите с кем создать битву: ");
        System.out.println("1. Огр");
        System.out.println("2. Гоблин");
        return readIntInRange(1,2);
    }

    public int showChooseFightersCliff() {
        System.out.println("\nВыберите с кем создать битву: ");
        System.out.println("1. Гарпия");
        System.out.println("2. Циклоп");
        return readIntInRange(1,2);
    }

    public void here() {System.out.println("\nВот тут! Прямо здесь! Прямо в этой части кода!");}
    public void wrong() {System.out.println("\nВведите правильное значение.");}

    // Снаряжение
    public void showHeroDnDDamage(PlayableHeroes hero) {System.out.print(hero.getSword().getDamage().getCount() + "d" + hero.getSword().getDamage().getSides() + " + " + hero.getSword().getDamage().getBonus() + "\n");}
    public void showEquipmentDnDDamage(Equipment equipment) {System.out.print(equipment.getDamage().getCount() + "d" + equipment.getDamage().getSides() + " + " + equipment.getDamage().getBonus() + "\n");}
    public void showItemName(Item item) {System.out.println("\nИмя предмета: " + item.getName());}
    public void showCost(Item item) {System.out.println("\nСтоимость предмета: " + item.getCost());}
    public void showCount(Item item) {System.out.println("\nКоличество предмета: " + item.getCount());}

    public int showChooseEquip(PlayableHeroes hero) {
        System.out.println("\nВот имеющееся снаряжение: ");
        showInventoryWithName(hero);
        System.out.println("1. Поменять оружие");
        System.out.println("2. Поменять броню");
        return readIntInRange(1,2);
    }

    // Инвентарь и магазин
    public int showInventoryMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Выбросить предмет");
        System.out.println("2. Посмотреть описание предмета");
        System.out.println("0. Выйти");
        return readIntInRange(0,2);
    }

    public int showBuyMenu() {
        System.out.println("\nКупить предмет?");
        System.out.println("1. Купить");
        System.out.println("2. Отказаться");
        return  readIntInRange(1,2);
    }

    public void noEquip() { System.out.println("\nУ вас нет снаряжения!");}
    // Вывод инвентаря в одну строчку
    public void showInventoryWithName(PlayableHeroes hero) {System.out.println("\n" + hero.getInventoryWithName());}
    public void zeroCount() { System.out.println("\nУ вас нет этого предмета!"); }
    public void showPurchaseResult(PurchaseResult purchaseResult) {System.out.println(purchaseResult);}
    public void whatYouWantBuy() {System.out.println("\nВыберите что хотите купить");}
    public void successBuying() {System.out.println("\nДзынь! Успешная покупка!");}
    public void notEnoughMoney() {System.out.println("\nНе хватает денег!");}
    public void deniedBuying() {System.out.println("\nОтложим покупку на следующий раз");}
    public void notInShopStock() {System.out.println("Предмета нет в магазине");}

    public void showShopStock(Shop shop) {
        for (int i = 0; i < shop.getShopSize(); i++) {
            // Этапы обращения: магазин - всё снаряжение магазина - конкретный предмет - его стоимость
            System.out.println(i+1 + "." + "Купить " + shop.getShopItems().get(i).getName() + " за " + shop.getShopItems().get(i).getCost() + " Осталось " + shop.getShopItems().get(i).getCount());
        }
    }

    // Вывод 1 типа предметов как инвентарь
    public void showItemList(Inventory items, ItemType itemType) {
        if (items.isEmpty()) {
            System.out.println("\nУ вас нет " + itemType);
            return;
        }
        System.out.println("\nВаши " + itemType + ":");
        for (int i = 0; i < items.getSize(); i++) {
            Item p = items.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void showItemData(Item item) {
        System.out.println("\n" + item.getName());
        System.out.println(item.getDescription());
        if (Objects.equals(item.getItemType(), ItemType.SWORD)) {
            showSwordData( (Equipment) item);
        } else if (Objects.equals(item.getItemType(), ItemType.ARMOR)) {
            showArmorData( (Equipment) item);
        } else if (Objects.equals(item.getItemType(), ItemType.POTION)) {
            showPotionData( (Potion) item);
        }
    }

    public void showSwordData(Equipment equipment) {
        showEquipmentDnDDamage(equipment);
    }

    public void showArmorData(Equipment equipment) {
        System.out.println("Снижение урона на " + equipment.getArmorCount());
    }

    public void showPotionData(Potion potion) {
        if (Objects.equals(potion.getSubType(), ItemType.POISON)) {
            Poison poison = (Poison) potion;
            System.out.println("Ядовитое отравление на " + poison.getDmgAmount() + " урона");
        } else if (Objects.equals(potion.getSubType(), ItemType.HEAL_POTION)) {
            HealPotion healPotion = (HealPotion) potion;
            System.out.println("Приятное облегчение на " + healPotion.getHealAmount() + " хп");
        }
    }

    //Вспомогательное

    // Проверяет ли является число целыми число ли вообще. Если не является - заставляет вводить заново
    // Если является - возвращает true
    private boolean isIntInput() {
        do {
            if (scanner.hasNextInt()) return true;
            else {
                System.out.println("\nЭто не число. Попробуйте снова.");
                scanner.next();
                return false;
            }
        } while (false);
    }


    // Функция для возврата числа
    // View должен принимать и обрабатывать внутри себя введённые данные
    // Пример: int choose = view.readIntInRange(1,5). Запрашивает число.
    // Ждёт от 1 до 5, ввели 8, просит ввести ещё раз.
    // Ввели 4, подходит, возвращает 4
    public int readIntInRange(int min, int max) {
        while (true) {
            System.out.print("Ваш выбор: ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Введите число от " + min + " до " + max);
        }
    }
}

