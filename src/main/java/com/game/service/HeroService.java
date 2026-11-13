package com.game.service;

import com.game.model.heroes.Hero;
import com.game.model.heroes.HeroCreateRequest;
import com.game.model.heroes.HeroResponse;
import com.game.repository.HeroRepository;

public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    // Соединяет Hero и HeroResponse
    // Берёт Hero, читает его поля и передаёт их в HeroResponse
    // Это происходит на сервере
    private HeroResponse toResponse(Hero hero) {
        return new HeroResponse(
            hero.getId(),
                hero.getMaxHp(),
                hero.getHp(),
                hero.getName(),
                hero.getMoney(),
                hero.getHeal()
        );
    }

    public HeroResponse create(HeroCreateRequest request) {
        Hero hero = new Hero();
        hero.setMaxHp(request.maxHp());
        hero.setHp(request.hp());
        hero.setName(request.name());
        hero.setMoney(request.money());
        hero.setHeal(request.heal());

        Hero saved = heroRepository.save(hero);
        return toResponse(saved);
    }
}