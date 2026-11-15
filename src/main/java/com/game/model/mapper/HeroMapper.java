package com.game.model.mapper;

import com.game.model.entity.HeroEntity;
import com.game.model.heroes.Hero;

public class HeroMapper {
    public static HeroEntity toEntity(Hero hero) {
        HeroEntity e = new HeroEntity();
        e.setName(hero.getName());
        e.setMaxHp(hero.getMaxHp());
        e.setHp(hero.getHp());
        e.setMoney(hero.getMoney());
        e.setHeal(hero.getHeal());
        return e;
    }

    public static Hero toDomain(HeroEntity e) {
        Hero hero = new Hero();
        hero.setName(e.getName());
        hero.setMaxHp(e.getMaxHp());
        hero.setHp(e.getHp());
        hero.setMoney(e.getMoney());
        hero.setHeal(e.getHeal());
        return hero;
    }
}
