import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main (String[] args) {
        Hero hero = new Hero();
        Logic logic = new Logic();
        Shop shop = new Shop();
        Battle battle1 = new Battle();
        View view = new View();


        Controller controller = new Controller(view, logic, hero, shop, battle1);
        controller.run();

        /*
        // Реализация интерфейса с пользователем
        Scanner scanner = new Scanner(System.in);


        shop.initializeShop();

        // Создаю новое сражение

        while (true) {
            logic.maimMenu();
            int choice_main = scanner.nextInt();
            if (choice_main == 0) { break; }
            switch (choice_main) {
                case 1 -> logic.chooseFighters(hero, battle1);
                case 2-> logic.chooseLocation(hero);
                case 3 -> shop.buyItems(hero);
                case 4 -> logic.fullEquip(hero, logic);
                case 5 -> logic.showStats(hero);
            }
        }

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
        System.out.println("\n" + target.getName() + " Получает " + getDmg() + " урона!");
        System.out.println("Здоровье " + target.getName() + ": " + target.getHp());
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

    // Просто легендарно. Сам всё создал и понял. И сработало идеально. Создаём отдельный массив только для вывода названий
    public List<String> getInventoryWithName() {
        List<String> arrayWithNames = new ArrayList<>();
        for (Item i : inventory.getItems()) {
            arrayWithNames.add(i.getName());
        }
        return arrayWithNames;
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
            System.out.println("\nВы должны быть в лесу чтобы начать битву\n");
            return false;
        }
    }
    public boolean isShop() {
        if (getLocation().equals(Locations.SHOP)) {
            return true;
        } else {
            System.out.println("\nВы должны быть в магазине чтобы торговать\n");
            return false;
        }
    }
    public void goForest() {
        location = Locations.FOREST;
        System.out.println("\nВы в лесу!\n");
    }

    public void goShop() {
        location = Locations.SHOP;
        System.out.println("\nВы в магазине!\n");
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
        if (items.size() < max_size) {
            items.add(item);
        } else { System.out.println("Инвентарь забит, места нет!"); }

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

class Logic {
    Scanner scanner = new Scanner(System.in);



}


class Battle {
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
                        choosePotion(hero);
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
}

// Абстрактный метод для всех предметов вообще
abstract class Item {
    private ItemType itemType;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}

enum ItemType {
    SWORD,
    ARMOR,
    POTION
}


// Начинаю реализацию MVC - Model Controller View
// Класс View отвечает за все выводимые данные, то есть каждые print будет тут
// Класс Model не будет знать как выводить, он лишь будет передавать данные
// Все вызовы будет производить Controller, Model лишь будет выполнять техническую часть
class View {
    public void maimMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Создать битву");
        System.out.println("2. Сменить локацию");
        System.out.println("3. Закупиться в магазине");
        System.out.println("4. Экипировать снаряжение");
        System.out.println("5. Вывести информацию о герое");
        System.out.println("0. Выйти");
    }
    public void showChooseFighters() {
        System.out.println("\nВыберите с кем создать битву");
        System.out.println("1. Огр");
        System.out.println("2. Гоблин");
    }
    public void showChooseLocation() {
        System.out.println("\n1. Магазин");
        System.out.println("2. Лес");
    }
    public void showChooseEquip() {
        System.out.println("\nВот имеющееся снаряжение: ");
        System.out.println("1. Поменять оружие");
        System.out.println("2. Поменять броню");
    }
    public void showStats(PlayableHeroes hero) {
        System.out.println("1. Имя: " + hero.getName());
        System.out.println("2. HP: " + hero.getHp());
        System.out.println("3. Max_HP: " + hero.getMax_hp());
        System.out.println("4. Урон:" + hero.getDmg());
        System.out.println("5. Деньги: " + hero.getMoney());
        System.out.println("6. Меч: " + hero.getSword().getName());
        System.out.println("7. Броня: " + hero.getArmor().getName());
        System.out.println("8. Сопротивление урону: " + hero.getReduce_damage() + "\n");
    }
    public void showName(Fighter fighter) {System.out.println("Имя: " + fighter.getHp());}
    public void showHP(Fighter fighter) {System.out.println("HP: " + fighter.getHp());}
    public void showMaxHP(Fighter fighter) {System.out.println("Max_HP: " + fighter.getMax_hp());}
    public void showDmg(Fighter fighter) {System.out.println("Урон: " + fighter.getDmg());}
    public void showMoney(Fighter fighter) {System.out.println("Деньги: " + fighter.getMoney());}
    public void showSword(Fighter fighter) {System.out.println("Меч: " + fighter.getSword());}
    public void showArmor(Fighter fighter) {System.out.println("Броня: " + fighter.getArmor());}
    public void showReduceDamage(Fighter fighter) {System.out.println("Снижение урона: " + fighter.getReduce_damage());}


    public void showPotionList(List<Item> potions) {
        if (potions.isEmpty()) {
            System.out.println("У вас нет зелий.");
            return;
        }
        System.out.println("Ваши зелья:");
        for (int i = 0; i < potions.size(); i++) {
            Item p = potions.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void showSwordList(List<Item> swords) {
        if (swords.isEmpty()) {
            System.out.println("У вас нет мечей.");
            return;
        }
        System.out.println("Ваши мечи:");
        for (int i = 0; i < swords.size(); i++) {
            Item p = swords.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void showArmorList(List<Item> armors) {
        if (armors.isEmpty()) {
            System.out.println("У вас нет брони.");
            return;
        }
        System.out.println("Ваша броня:");
        for (int i = 0; i < armors.size(); i++) {
            Item p = armors.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (осталось: " + p.getCount() + ")");
        }
    }

    public void noEquip() { System.out.println("У вас нет снаряжения!");}
    public void showInventaryWithName(Hero hero) {System.out.println(hero.getInventoryWithName());}

}

class Controller {
    Scanner scanner = new Scanner(System.in);
    private final View view;
    private final Logic logic;
    private final Hero hero;
    private final Shop shop;
    private final Battle battle1;

    public Controller(View view, Logic logic, Hero hero, Shop shop, Battle battle1) {
        this.view = view;
        this.logic = logic;
        this.hero = hero;
        this.shop = shop;
        this.battle1 = battle1;

    }

    void run() {
        while (true) {
            view.maimMenu();
            int choice_main = scanner.nextInt();
            if (choice_main == 0) { break; }
            switch (choice_main) {
                case 1 -> chooseFighters();
                case 2-> chooseLocation();
                case 3 -> shop.buyItems(hero);
                case 4 -> fullEquip();
                case 5 -> view.showStats(hero);
            }
        }
    }
    public void chooseFighters() {
        view.showChooseFighters();
        int choice_create_fight = scanner.nextInt();
        switch (choice_create_fight) {
            case 1 -> {
                Ogre ogre = new Ogre();
                battle1.battle(hero, ogre);
            }
            case 2 -> {
                Goblin goblin = new Goblin();
                battle1.battle(hero, goblin);
            }
        }
    }
    public void chooseLocation() {
        view.showChooseLocation();
        int choice_location = scanner.nextInt();
        switch (choice_location) {
            case 1 -> hero.goShop();
            case 2 -> hero.goForest();
        }
    }

    public boolean fullEquip() {
        view.showInventaryWithName(hero);
        if (hero.getInventory().isEmpty()) {
            view.noEquip();
            // Придумать что сделать вместо break
            // break;
            return false;
        }
        view.showChooseEquip();
        int choice_swap_equip = scanner.nextInt();
        switch (choice_swap_equip) {
            case 1 -> chooseSwords(hero);
            case 2 -> chooseArmor(hero);
        }
        return true;
    }
    //Функция создания инвентаря с каким-то конкретным типом предметов. Возвращает массив предметов одного типа, которые есть в инвентаре
    public List<Item> createOneTypeInventory(PlayableHeroes hero, ItemType itemType) {
        ArrayList<Item> OneTypeInventory = new ArrayList<>();
        for (Item i: hero.getInventory()) {
            if (i.getItemType().equals(itemType)) {
                OneTypeInventory.add(i);
            }
        }

        return OneTypeInventory;
    }

    // Выбирает из массива со всем снаряжением мечи и создаёт массив с мечами
    public void chooseSwords(PlayableHeroes hero) {
        Scanner scanner = new Scanner(System.in);
        // Создаём инвентарь с мечами
        List<Item> swords = createOneTypeInventory(hero, ItemType.SWORD);
        view.showSwordList(swords);

        int choice_equip_from_inventory = scanner.nextInt();
        if (choice_equip_from_inventory <= swords.size()) {
            System.out.println(hero.getDmg());
            hero.equipSword((Equipment) swords.get(choice_equip_from_inventory-1));
            System.out.println(hero.getDmg());
        }
    }
    public void chooseArmor(PlayableHeroes hero) {
        // Отделяем мечи от всего снаряжения и даём им тип Equipment

        List<Item> armors = createOneTypeInventory(hero, ItemType.ARMOR);
        view.showArmorList(armors);

        int choice_equip_from_inventory = scanner.nextInt();
        if (choice_equip_from_inventory <= armors.size()) {
            view.showReduceDamage(hero);
            hero.equipArmor((Equipment) armors.get(choice_equip_from_inventory-1));
            view.showReduceDamage(hero);
        }
    }

    public Potion choosePotion(PlayableHeroes hero) {
        List<Item> potions = createOneTypeInventory(hero, ItemType.POTION);
        view.showPotionList(potions);

        int choice_equip_from_inventory = scanner.nextInt();
        if (choice_equip_from_inventory <= potions.size()) {
            System.out.println(hero.getHp());
            hero.drinkPotion((Potion) potions.get(choice_equip_from_inventory-1));
            System.out.println(hero.getHp());
        }
        return null;
    }
}

// Сделать чтобы снаряжение складывалось в инвентарь и оттуда доставалось. Потому что хз как по-другому сделать проверки в isPurchased
abstract class Equipment extends Item {
    private int dmg;
    private int armor;

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
        setItemType(ItemType.SWORD);
        incCount();
    }
}

class Skin extends Equipment {
    public Skin() {
        setName("Skin");
        setArmor(0);
        setItemType(ItemType.ARMOR);
        incCount();
    }
}

class IronSword extends Equipment {
    public IronSword() {
        setName("Iron sword");
        setDmg(4);
        setCost(50);
        setItemType(ItemType.SWORD);
    }
}
class GoldSword extends Equipment {
    public GoldSword() {
        setName("Gold sword");
        setDmg(5);
        setCost(120);
        setItemType(ItemType.SWORD);
    }
}
class IronChest extends Equipment {
    public IronChest() {
        setName("IronChest");
        setCost(200);
        setArmor(1);
        setItemType(ItemType.ARMOR);
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
        setItemType(ItemType.POTION);
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
        setItemType(ItemType.POTION);
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
                System.out.println("\nДзынь! Успешная покупка!");
            } else { System.out.println("Не хватает денег!"); }
        }
    }

    // Создание списка покупок и возможности выбора одного предмета
    public void buyItems(PlayableHeroes hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВаши монеты: " + hero.getMoney());
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