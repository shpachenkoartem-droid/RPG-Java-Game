package com.game.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DamageDiceEntityTest {
    private DamageDiceEntity damageDiceEntity;

    @BeforeEach
    void setUp() {
        damageDiceEntity = new DamageDiceEntity();
    }

    @Test
    void show() {
        System.out.println(damageDiceEntity.getId());
    }
}