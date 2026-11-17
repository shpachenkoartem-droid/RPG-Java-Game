package com.game.service;

import com.game.model.entity.HeroEntity;
import com.game.model.heroes.Hero;
import com.game.model.createrequest.HeroCreateRequest;
import com.game.model.response.HeroResponse;
import com.game.model.mapper.HeroMapper;
import com.game.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;


// Читается так же как и @Component
// То есть Spring при старте создаёт его экземпляр
@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    // Превращает HeroEntity (строку таблицы в БД) в
    // HeroResponse (DTO для клиента, класс, описывающий какие данные передаются между клиентом и сервером)
    // Создаёт record-класс с разрешёнными полями
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

    // Реализует POST
    // Принимает данные от клиента
    // Создаёт нового героя и сохраняет в БД
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

    // Реализует READ
    // Читает всех героев из БД и возвращает список
    public List<HeroResponse> findAll() {
        return heroRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}