package com.game.service;

import com.game.model.entity.HeroEntity;
import com.game.model.heroes.Hero;
import com.game.model.heroes.HeroCreateRequest;
import com.game.model.heroes.HeroResponse;
import com.game.model.mapper.HeroMapper;
import com.game.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    // Соединяет Hero и HeroResponse
    // Берёт Hero, читает его поля и передаёт их в HeroResponse
    // Это происходит на сервере
    private HeroResponse toResponse(HeroEntity entity) {
        return new HeroResponse(
            entity.getId(),
                entity.getMaxHp(),
                entity.getHp(),
                entity.getName(),
                entity.getMoney(),
                entity.getHeal()
        );
    }

    public HeroResponse create(HeroCreateRequest request) {
        // DTO
        Hero hero = new Hero();
        hero.setMaxHp(request.maxHp());
        hero.setHp(request.hp());
        hero.setName(request.name());
        hero.setMoney(request.money());
        hero.setHeal(request.heal());

        // От домена к сущности через маппер
        HeroEntity entity = HeroMapper.toEntity(hero);

        // Сохранить
        HeroEntity saved = heroRepository.save(entity);

        // От сущности к DTO
        return toResponse(saved);
    }

    public List<HeroResponse> findAll() {
        return heroRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}