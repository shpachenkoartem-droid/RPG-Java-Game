package com.game.model.entity;

import com.game.enums.EquipmentType;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int armorCount;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @ManyToOne
    // Внешний ключ - damage_dice_id
    @JoinColumn(name = "damage_dice_id")
    private DamageDiceEntity damage;

    public EquipmentEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DamageDiceEntity getDamage() {
        return damage;
    }

    public void setDamage(DamageDiceEntity damage) {
        this.damage = damage;
    }

    public int getArmorCount() {
        return armorCount;
    }

    public void setArmorCount(int armorCount) {
        this.armorCount = armorCount;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }
}
