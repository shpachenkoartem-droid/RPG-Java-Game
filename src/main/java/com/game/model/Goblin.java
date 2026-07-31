package main.java.com.game.model;

// Дочерний класс от бойца
public class Goblin extends Fighter {
    public Goblin() {
        setMax_hp(5);
        setHp(5);
        setDmg(2);
        setName("Гоблин");
        setMoney(10);
    }
    // Функции из main.java.com.game.Goblin можно вызывать только для объектов main.java.com.game.Goblin, для main.java.com.game.Fighter нельзя
}
// Порядок: Вызываем SetHp(5) -> вызывается this.hp = hp -> запись в private int hp = 5
// hp справа это наше передаваемое значение, this это ссылка на текущий объект, в нашем случае установка hp в такое же поле класса
