public class Main {
    static void main (String[] args) {

        Hero hero = new Hero(); // Создаю героя
        Goblin goblin1 = new Goblin(); // Создаю нового гоблина
        Goblin goblin2 = new Goblin();
        Ogre ogre = new Ogre();

        IronSword sword1 = new IronSword();
        GoldSword sword2 = new GoldSword();
        IronChest iron_chest = new IronChest();
        HealPotion healPotion = new HealPotion();
        Poison poison = new Poison();


        hero.equipSword(sword1);
        hero.equipSword(sword2);
        hero.equipArmor(iron_chest);
        hero.drinkPotion(healPotion);
        hero.drinkPotion(poison);
        System.out.println(poison.getCount());

        // Сражение
        Battle battle1 = new Battle(); // Создаю новое сражение
        battle1.battle(hero, ogre);

        System.out.println(hero.getMoney());
        hero.buyPotion(healPotion);
        System.out.println(hero.getMoney());
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
        if (equipment.isPurchased()) {
            this.sword = equipment;
            setDmg(equipment.getDmg());
        } else { System.out.println("У вас нет такого меча"); }
        // Как работает экипировка мечей
        // С помощью конструкторов мы создаём каждый меч
        // Fighter получает метод equipSword где в параметрах указывается абстрактный класс всех предметов Equipment, а значит и в частности наш меч
        // Поставили урон с помощью set, "протащили" значение от нашего конструктора меча, подход вроде правильный, но не уверен


    }
    public void equipArmor(Equipment equipment) {
        if (equipment.isPurchased()) {
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

    /*
    // Если мы используем getHp(), то вернётся хп лечащего, поэтому надо target.getHp
    // С добавлением incHp упростил строку с target.setHp(target.getHp() + getHealAmount()) до target.incHp(getHealAmount())

    public void heal(Fighter target) {
        System.out.println("Лечение!");
        target.incHp(getHeal());
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        if (heal <= 0) { System.out.print("Лечение должно быть больше 0"); }
        else { this.heal = heal; }
    }
     */

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

// Healer гораздо более гибкий чем BaseHealer
// BaseHealer может лечить, но! это прежде всего боец, так как наследовал всё от Fighter
// Если создавать лечащий артефакт, то он будет наследоваться от интерфейса Healer
interface Healer {
    void heal (Fighter target);
}

abstract class BaseHealer extends Fighter implements Healer {
    private int heal;
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
        setMoney(70);
    }
}

class Hero extends BaseHealer implements Healer{
    public Hero() {
        setMax_hp(50);
        setHp(50);
        setDmg(3);
        setName("Герой");
        setMoney(0);
        setHeal(2);
    }

    public void buyPotion(Potion potion) {
        if (getMoney() > potion.getCost()) {
            potion.incCount();
            decMoney(potion.getCost());
        } else { System.out.println("Не хватает денег на зелье!"); }
    }
    public void buyEquipment(Equipment equipment) {
        if (getMoney() > equipment.getCost()) {
            equipment.setPurchased(true);
            decMoney(equipment.getCost());
        } else { System.out.println("Не хватает денег на снаряжение!"); }
    }
}
class Paladin extends BaseHealer {
    public Paladin() {
        setMax_hp(55);
        setHp(55);
        setDmg(2);
        setName("Паладин");
        setMoney(10);
        setHeal(4);
    }
}


class Battle {
    public void battle(Fighter fighter1, Fighter fighter2) {
        System.out.print("Изначальное здоровье " + fighter1.getName() + ": " + fighter1.getHp() + "\n");
        System.out.println("Изначальное здоровье " + fighter2.getName() + ": " + fighter2.getHp() + "\n");
        while (fighter1.getHp()>0 && fighter2.getHp()>0) {
            fighter1.attack(fighter2);
            fighter2.attack(fighter1);
            System.out.println("Обменялись ударами!" + "\n" +"Здоровье " + fighter1.getName() + ": " + fighter1.getHp() + "\n" + "Здоровье " + fighter2.getName() + ": " + fighter2.getHp());
        }

        // Результаты битвы
        if (fighter2.getHp() == 0) { System.out.println(fighter2.getName() + " побеждён!");}
        if (fighter1.getHp() == 0) { System.out.println(fighter1.getName() + " побеждён!");}

        // Получение денег
        fighter1.incMoney(fighter2.getMoney());
    }
    public void treatAll (Healer healer, Fighter minion) {
        healer.heal(minion);
    }
}

// Сделать чтобы снаряжение складывалось в инвентарь и оттуда доставалось. Потому что хз как по-другому сделать проверки в isPurchased
abstract class Equipment {
    private String name;
    private int dmg;
    private int cost;
    private int armor;
    private boolean purchased;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}

class IronSword extends Equipment {
    public IronSword() {
        setName("Iron sword");
        setDmg(4);
        setCost(50);
        setPurchased(false);
    }
}
class GoldSword extends Equipment {
    public GoldSword() {
        setName("Gold sword");
        setDmg(5);
        setCost(120);
        setPurchased(false);
    }
}
class IronChest extends Equipment {
    public IronChest() {
        setName("IronChest");
        setCost(200);
        setArmor(1);
        setPurchased(false);
    }
}
abstract class Potion {
    // Абстрактный метод - каждое зелье реализуется по-своему
    // Эта реализация должна быть у каждого. Неплохая вещь чтобы избежать дублирования кода
    public abstract void apply (Fighter target);
    private String name;
    private int count;
    private int cost;

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

    public boolean drink() {
        if (getCount() > 0) {
            setCount(getCount() - 1);
            return true;
        } else {
            System.out.println("Зелий нет!");
            return false;
        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


}
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
    public void apply (Fighter target) {
        // Если 0, то выводится сообщение что нет зелий, сделать это в отдельном методе
        if (drink()) {
            target.decHp(getDmgAmount());
        }
    }
}

class Shop {
    IronSword sword1 = new IronSword();
    GoldSword sword2 = new GoldSword();
    IronChest iron_chest = new IronChest();
    HealPotion healPotion = new HealPotion();
    Poison poison = new Poison();

}


// Цели - сделать битву против врага по выбору. мол инициализировать битву и указать только тех, кто сражается. после этого сделать торговца и дальнейший сюжет

// Задача на потом - сделать так что можно будет получать деньги не с помощью if (fighter2.getName() == "Гоблин") { fighter1.incMoney(10); }
// А с помощью fighter2.getForGoblin() В Fighter нельзя делать такой метод, ведь методы в Fighter должны подходить ко всем наследникам
// Это принцип подстановки Барбары Лисков (LSP)

// Вместе с торговцем сделать отдельные панельки для зелий и заклинаний с их стоимостью и эффектом
// дать возможность использовать зелья герою между ходами