import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    static void main (String[] args) {

        // Реализация интерфейса с пользователем
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop();
        shop.initializeShop();
        Hero hero = new Hero();

        // Создаю новое сражение
        Battle battle1 = new Battle();
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать битву");
            System.out.println("2. Сменить локацию");
            System.out.println("3. Закупиться в магазине");
            System.out.println("4. Экипировать снаряжение");
            System.out.println("5. Вывести информацию о герое");
            System.out.println("0. Выйти");
            int choice = scanner.nextInt();
            if (choice == 0) { break;}

            switch (choice) {
                case 1 -> {
                    System.out.println("Выберите с кем создать битву");
                    System.out.println("1. Огр");
                    System.out.println("2. Гоблин");

                    int choice2 = scanner.nextInt();
                    switch (choice2) {
                        case 1 -> {
                            Ogre ogre = new Ogre();
                            battle1.battle(hero, ogre);
                        }
                        case 2 -> {
                            System.out.println(hero.getInventory());
                            Goblin goblin = new Goblin();
                            battle1.battle(hero, goblin);
                        }
                    }
                }
                case 2-> {
                    System.out.println("1. Магазин");
                    System.out.println("2. Лес");

                    int choice3 = scanner.nextInt();
                    switch (choice3) {
                        case 1 -> hero.goShop();
                        case 2 -> hero.goForest();
                    }
                }
                case 3 -> shop.buyItems(hero);
                case 4 -> {
                    System.out.println("Вот имеющееся снаряжение: ");
                    System.out.println(hero.getInventory());
                    System.out.println("1. Поменять оружие");
                    System.out.println("2. Поменять броню");
                    int choice3 = scanner.nextInt();
                        switch (choice3) {
                            case 1 -> {
                                ArrayList<Equipment> swords = new ArrayList<>();
                                System.out.println("Ваше оружие: " + hero.getSword());
                                // Пройтись по всему снаряжению, найти оружия и вывести их сюда
                                for (int i = 0; i < hero.getLenInventory(); i++) {
                                    // Считаем что поступающий нам предмет это какой-то Item
                                    Item one_from_inventory = hero.getInventoryByNumber(i);
                                    // Проверяем если объект является экземпляром класса Equippable
                                    if (one_from_inventory instanceof Equippable equippable) {
                                        // Надо привести one_from_inventory к equippable
                                        // Если true, тогда приводим к типу Equipable чтобы работать с его методами
                                        // Проверяем какой тип
                                        if (equippable.getEquipmentType() == EquipmentType.SWORD) {
                                            swords.add((Equipment) equippable);
                                        }
                                    }
                                }
                                System.out.println("Выберите что экипировать");
                                int k = 1;
                                for (Equipment j : swords) {
                                    System.out.println(k + " Меч " + j.getName());
                                    k++;
                                }

                                int choice4 = scanner.nextInt();
                                switch (choice4) {
                                    case 1 -> {
                                        System.out.println("Вы экипировали меч 1");

                                    }
                                    case 2 -> {
                                        System.out.println("Вы экипировали меч 2");
                                        System.out.println(hero.getDmg());
                                        hero.equipSword(swords.get(1));
                                        System.out.println(swords.get(1));
                                        System.out.println(hero.getDmg());
                                    }
                                }
                            }
                            case 2 -> {
                                System.out.println("Ваша броня: " + hero.getArmor());
                            }
                        }
                }
                case 5 -> {
                    System.out.println("1. Имя: " + hero.getName());
                    System.out.println("2. HP: " + hero.getHp());
                    System.out.println("3. Max_HP: " + hero.getMax_hp());
                    System.out.println("4. Урон:" + hero.getDmg());
                    System.out.println("5. Деньги: " + hero.getMoney());
                    System.out.println("6. Меч: " + hero.getSword().getName());
                    System.out.println("7. Броня: " + hero.getArmor().getName());
                }

            }
        }








        /*
         // Создаю героя
        Goblin goblin1 = new Goblin(); // Создаю нового гоблина
        Goblin goblin2 = new Goblin();


        IronSword sword1 = new IronSword();
        GoldSword sword2 = new GoldSword();
        IronChest iron_chest = new IronChest();
        HealPotion healPotion = new HealPotion();
        Poison poison = new Poison();


        System.out.println(poison.getCount());

        // Сражение
        hero.goForest();


        hero.heal(hero);
        Shop shop = new Shop();

        // Фигня полная, надо что-то сделать с тем откуда берутся зелья
        // И реализовать интерфейс для зелий и снаряжения
        // Но логика с покупкой зелий сработала!

        // Полная проверка работоспособности магазина
        // Вроде всё работает
        System.out.println("Начало теста магазина!!!!");
        System.out.println(hero.getMoney());
        shop.buyItems(hero, healPotion);
        hero.goShop();
        shop.buyItems(hero, healPotion);
        hero.drinkPotion(healPotion);
        System.out.println(hero.getHp());
        System.out.println("Пытаюсь выпить когда зелий нет!");
        hero.drinkPotion(healPotion);
        System.out.println(hero.getHp());
        System.out.println("Пытаюсь надеть броню!");
        shop.buyItems(hero, iron_chest);
        System.out.println("Вычет урона до надетой брони " + hero.getReduce_damage());
        hero.equipArmor(iron_chest);
        System.out.println("Вычет урона после надетой брони " + hero.getReduce_damage());

        System.out.println(healPotion.getCount());

        System.out.println(hero.getLocation());

         */

    }
}

// абстрактный класс бойца - "образ" всех кто должен сражаться
abstract class Fighter {
    private int hp;
    private int max_hp;
    private int dmg;
    private String name;
    private int money;
    // Чтобы не нарушать принцип подстановки Барбары Лисков (LSP) надо сделать класс наследника, который может реализовывать лечение (герой, враг-жрец и тп)
    // Потому что не каждый боец может лечить
    private int reduce_damage;
    private Equipment sword;
    private Equipment armor;

    public void attack(Fighter target) {
        target.decHp(getDmg() - target.getReduce_damage());
    }


    public void equipSword(Equipment equipment) {
        if (equipment.isNotNull()) {
            this.sword = equipment;
            setDmg(equipment.getDmg());
        } else { System.out.println("У вас нет такого меча"); }
        // Как работает экипировка мечей
        // С помощью конструкторов мы создаём каждый меч
        // Fighter получает метод equipSword где в параметрах указывается абстрактный класс всех предметов Equipment, а значит и в частности наш меч
        // Поставили урон с помощью set, "протащили" значение от нашего конструктора меча, подход вроде правильный, но не уверен
    }


    public void equipArmor(Equipment equipment) {
        if (equipment.isNotNull()) {
            this.armor = equipment;
            setReduce_damage(equipment.getArmor());
        } else { System.out.println("У вас нет такой брони"); }

    }

    public void drinkPotion(Potion potion) {
        // Зелье применяется на выпившего его
        potion.apply(this);
    }


// Геттеры и сеттеры нужны чтобы получать и изменять значения хп, атаки и так далее для моих бойцов из любого места
// Благодаря ним код следует принципам ИНКАПСУЛЯЦИИ
// Так как в Fighter поля private, то их нельзя изменить с помощью простого hero.hp = hero.hp + 5
// Теперь поля можно изменять с помощью hero.setHp(hero.getHp() + 5)
// К ним можно добавить защиту от отрицательного хп, слишком большого урона и бессмыслицы

// У нас есть класс в котором мы определили поля для него самого и всех дочерних классов.
// Дочерние классы более индивидуальны, для них уже выбираем конкретные значения.
// В других классах создав объект этих дочерних можно менять каждый объект отдельно

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            System.out.print("Здоровье должно быть больше 0");
        } else { this.hp = hp; }

    }

    public void incHp(int hp) {
        setHp(this.getHp() + hp);
        // Защита от превышения максимального здоровья
        if (getHp() > getMax_hp()) {
            setHp(getMax_hp());
        }
    }

    public void decHp(int hp) {
        if (getHp() - hp >= 0) {
            setHp(this.getHp() - hp);
        } else {
            setHp(0);
        }
    }

    public int getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        if (dmg <= 0) { System.out.print("Урон должен быть больше 0"); }
        else { this.dmg = dmg; }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // Хочу чтобы getMoney вызывалась "под капотом", и вместо fighter1.setMoney(fighter1.getMoney() + 10) было fighter1.incMoney(10)
// this обозначает вызвавшего
    public void incMoney(int money) {
        setMoney(this.getMoney() + money);
    }

    public void decMoney(int money) {
        if (getMoney() - money >= 0) {
            setMoney(this.getMoney() - money);
        } else {
            System.out.print("Недостаточно денег");
        }
    }

    public Equipment getSword() {
        return sword;
    }

    public void setSword(Equipment sword) {
        this.sword = sword;
    }

    public Equipment getArmor() {
        return armor;
    }

    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    public int getReduce_damage() {
        return reduce_damage;
    }

    public void setReduce_damage(int reduce_damage) {
        this.reduce_damage = reduce_damage;
    }
}

// Healer гораздо более гибкий чем PlayableHeroes
// PlayableHeroes может лечить, но! это прежде всего боец, так как наследовал всё от Fighter
// Если создавать лечащий артефакт, то он будет наследоваться от интерфейса Healer
interface Healer {
    void heal (Fighter target);
}

enum Locations {
    FOREST,
    SHOP
}

// Дал возможность героям ходить в локации. Долго думал как сделать, но по итогу вышло достаточно просто. Надо на бумаге рисовать примерные схемки.
abstract class PlayableHeroes extends Fighter implements Healer {
    private int heal;
    private Locations location = Locations.SHOP;
    private final Inventory inventory = new Inventory();

    public List<Item> getInventory() {
        return inventory.getItems();
    }

    public Item getInventoryByNumber(int number) {
        return getInventory().get(number);
    }

    public int getLenInventory() {
        return getInventory().toArray().length;
    }

    public void pickUp(Item item) {
        inventory.addItem(item);
    }

    public void drop(Item item) {
        inventory.removeItem(item);
    }

    public Locations getLocation() {
        return location;
    }

    public boolean isForest() {
        if (getLocation().equals(Locations.FOREST)) {
            return true;
        } else {
            System.out.println("Вы должны быть в лесу чтобы начать битву");
            return false;
        }
    }
    public boolean isShop() {
        if (getLocation().equals(Locations.SHOP)) {
            return true;
        } else {
            System.out.println("Вы должны быть в магазине чтобы торговать");
            return false;
        }
    }
    public void goForest() {
        location = Locations.FOREST;
        System.out.println("Вы в лесу!");
    }

    public void goShop() {
        location = Locations.SHOP;
        System.out.println("Вы в магазине!");
    }

    @Override
    public void heal(Fighter target) {
        target.incHp(getHeal());
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        if (heal <= 0) { System.out.print("Лечение должно быть больше 0"); }
        else { this.heal = heal; }
    }
}

// Дочерний класс от бойца
class Goblin extends Fighter {
    public Goblin() {
        setMax_hp(5);
        setHp(5);
        setDmg(2);
        setName("Гоблин");
        setMoney(10);
    }
    // Функции из Goblin можно вызывать только для объектов Goblin, для Fighter нельзя
}
// Порядок: Вызываем SetHp(5) -> вызывается this.hp = hp -> запись в private int hp = 5
// hp справа это наше передаваемое значение, this это ссылка на текущий объект, в нашем случае установка hp в такое же поле класса


class Ogre extends Fighter {
    public Ogre() {
        setMax_hp(10);
        setHp(10);
        setDmg(4);
        setName("Огр");
        setMoney(270);
    }
}

class Hero extends PlayableHeroes {
    Fist fist = new Fist();
    Skin skin = new Skin();
    // Создаём инвентарь только для одного героя
    // Все методы для воздействия реализуем в PlayableHeroes
    public Hero() {
        setMax_hp(50);
        setHp(50);
        setName("Герой");
        setMoney(1000);
        setHeal(2);
        equipSword(fist);
        equipArmor(skin);
    }
}

class Paladin extends PlayableHeroes {
    public Paladin() {
        setMax_hp(55);
        setHp(55);
        setDmg(2);
        setName("Паладин");
        setMoney(10);
        setHeal(4);
    }
}
class Inventory {
    private final List<Item> items = new ArrayList<>();
    private int max_size = 20;
    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public List<Item> getItems() {
        return items;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }
}

class Battle {
    public void battle(PlayableHeroes hero, Fighter fighter2) {
        if (hero.isForest()) {
            System.out.print("Изначальное здоровье " + hero.getName() + ": " + hero.getHp() + "\n");
            System.out.println("Изначальное здоровье " + fighter2.getName() + ": " + fighter2.getHp() + "\n");
            while (hero.getHp()>0 && fighter2.getHp()>0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Выберите действие");
                System.out.println("1. Атаковать");
                System.out.println("2. Выпить зелье");
                int move = scanner.nextInt();
                switch (move) {
                    case 1:
                        hero.attack(fighter2);
                        fighter2.attack(hero);
                        System.out.println("Обменялись ударами!" + "\n" + "Здоровье " + hero.getName() + ": " + hero.getHp() + "\n" + "Здоровье " + fighter2.getName() + ": " + fighter2.getHp());
                    case 2:
                }
                }


            // Результаты битвы
            if (fighter2.getHp() == 0) { System.out.println(fighter2.getName() + " побеждён!");}
            if (hero.getHp() == 0) { System.out.println(hero.getName() + " побеждён!");}

            // Получение денег
            hero.incMoney(fighter2.getMoney());
        }
    }
}

// Абстрактный метод для всех предметов вообще
abstract class Item {
    private String name;
    private int cost;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incCount() {
        setCount(getCount() + 1);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public boolean isNotNull() {
        return getCount() > 0;
    }


}

interface Equippable {
    EquipmentType getEquipmentType();
}

enum EquipmentType {
    SWORD,
    ARMOR
}

// Сделать чтобы снаряжение складывалось в инвентарь и оттуда доставалось. Потому что хз как по-другому сделать проверки в isPurchased
abstract class Equipment extends Item implements Equippable {
    private int dmg;
    private int armor;
    private EquipmentType equipmentType;

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    @Override
    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}

class Fist extends Equipment {
    public Fist() {
        setName("Fist");
        setDmg(3);
        setEquipmentType(EquipmentType.SWORD);
        incCount();
    }
}

class Skin extends Equipment {
    public Skin() {
        setName("Skin");
        setArmor(0);
        setEquipmentType(EquipmentType.ARMOR);
        incCount();
    }
}

class IronSword extends Equipment {
    public IronSword() {
        setName("Iron sword");
        setDmg(4);
        setCost(50);
        setEquipmentType(EquipmentType.SWORD);
    }
}
class GoldSword extends Equipment {
    public GoldSword() {
        setName("Gold sword");
        setDmg(5);
        setCost(120);
        setEquipmentType(EquipmentType.SWORD);
    }
}
class IronChest extends Equipment {
    public IronChest() {
        setName("IronChest");
        setCost(200);
        setArmor(1);
        setEquipmentType(EquipmentType.ARMOR);
    }
}


abstract class Potion extends Item {
    // Абстрактный метод - каждое зелье реализуется по-своему
    // Эта реализация должна быть у каждого. Неплохая вещь чтобы избежать дублирования кода
    public abstract void apply (Fighter target);

    // Здесь drink это внутренняя функция, если захотим чтобы существо пило зелье, то оно будет hero.drinkPotion
    public boolean drink() {
        if (isNotNull()) {
            setCount(getCount() - 1);
            return true;
        } else {
            System.out.println("Зелий нет!");
            return false;
        }
    }

}

// В целом можно было бы сделать зелью зависимось от интерфейса Healer, но у него уже есть асбтрактный метод apply
class HealPotion extends Potion {
    private int healAmount = 0;
    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
    // Что за зелье и что оно делает
    public HealPotion() {
        setName("Heal potion");
        setHealAmount(8);
        setCost(30);
    }
    // Его непосредственное применение
    @Override
    public void apply(Fighter target) {
        // Если уменьшили количество
        if (drink()) {
            // Реализация того что делает зелье
            target.incHp(getHealAmount());
        }
    }
}

class Poison extends Potion {
    private int dmgAmount = 0;
    public int getDmgAmount() {
        return dmgAmount;
    }

    public void setDmgAmount(int dmgAmount) {
        this.dmgAmount = dmgAmount;
    }
    public Poison() {
        setName("Poison");
        setDmgAmount(5);
        setCost(40);
    }
    @Override
    public void apply (Fighter target) {
        // Если 0, то выводится сообщение что нет зелий, сделать это в отдельном методе
        if (drink()) {
            target.decHp(getDmgAmount());
        }
    }
}

class Shop {

    private final List<Item> shopItems = new ArrayList<>();
    public void addShopItems(Item item) {
        shopItems.add(item);
    }

    // Для всех предметов магазина
    public List<Item> getShopItems() {
        return shopItems;
    }

    // Для одного предмета магазина
    public Item getShopItem(int number) {
        return shopItems.get(number);
    }

    public int getShopSize() {
        return shopItems.size();
    }

    public void initializeShop() {
        addShopItems(sword1);
        addShopItems(sword2);
        addShopItems(iron_chest);
        addShopItems(healPotion);
        addShopItems(poison);
    }
    // Наполняем магазин товарами
    private final IronSword sword1 = new IronSword();
    private final GoldSword sword2 = new GoldSword();
    private final IronChest iron_chest = new IronChest();
    private final HealPotion healPotion = new HealPotion();
    private final Poison poison = new Poison();

    // Сам процесс покупки когда уже выбрали предмет
    public void buySoloItem(PlayableHeroes hero, Item item) {
        if (hero.isShop()) {
            if (hero.getMoney() > item.getCost()) {
                item.incCount();
                hero.decMoney(item.getCost());
                hero.pickUp(item);
                System.out.println("Дзынь! Успешная покупка!");
            } else { System.out.println("Не хватает денег!"); }
        }
    }

    // Создание списка покупок и возможности выбора одного предмета
    public void buyItems(PlayableHeroes hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ваши монеты: " + hero.getMoney());
        // Если в магазине, хз можно ли сделать проверку проще, вроде и так хорошо
        System.out.println("Выберите что хотите купить");
        for (int i = 0; i < getShopSize(); i++) {
            // Этапы обращения: магазин - всё снаряжение магазина - конкретный предмет - его стоимость
            System.out.println(i+1 + "." + "Купить " + getShopItems().get(i).getName() + " за " + getShopItems().get(i).getCost());
        }
        int choice_items = scanner.nextInt();
        switch (choice_items) {
            case 1 -> buySoloItem(hero, getShopItem(0));
            case 2 -> buySoloItem(hero, getShopItem(1));
            case 3 -> buySoloItem(hero, getShopItem(2));
            case 4 -> buySoloItem(hero, getShopItem(3));
            case 5 -> buySoloItem(hero, getShopItem(4));
        }
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
// А с помощью fighter2.getForGoblin() В Fighter нельзя делать такой метод, ведь методы в Fighter должны подходить ко всем наследникам
// Это принцип подстановки Барбары Лисков (LSP)

// Вместе с торговцем сделать отдельные панельки для зелий и заклинаний с их стоимостью и эффектом
// Дать возможность использовать зелья герою между ходами