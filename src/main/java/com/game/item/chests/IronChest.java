package main.java.com.game.item.chests;

import main.java.com.game.enums.ItemType;
import main.java.com.game.item.Equipment;

public class IronChest extends Equipment {
    public IronChest() {
        setName("Железный нагрудник");
        setCost(200);
        setArmorCount(1);
        setItemType(ItemType.ARMOR);
        setDescription("Крепко выглядящий железный нагрудник, опускающийся чуть ниже пояса. Такой может спасти жизнь.");
    }
}

