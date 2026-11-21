package com.game.factory;

import com.game.enums.EquipmentType;
import com.game.item.Equipment;
import com.game.item.chests.IronChest;
import com.game.item.chests.Skin;
import com.game.item.weapons.GoldSword;
import com.game.item.weapons.IronSword;

public class EquipmentFactory {
    public static Equipment create(EquipmentType type) {
        return switch (type) {
            case IRON_SWORD -> new IronSword();
            case GOLD_SWORD -> new GoldSword();
            case IRON_CHEST -> new IronChest();
            case SKIN -> new Skin();
        };
    }
}
