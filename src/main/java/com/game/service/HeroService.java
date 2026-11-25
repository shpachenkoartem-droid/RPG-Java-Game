package com.game.service;

import com.game.coordinates.Coordinates;
import com.game.coordinates.CoordinatesEmbeddable;
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
// Обрабатывает запросы и создаёт героев
@Service
public class HeroService {

    // Здесь не создаётся новое heroRepository
    // Spring сам находит объект heroRepository
    // И передаёт его в конструктор
    // Это Dependency Injection — внедрение зависимостей
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
                entity.getHeal(),
                HeroMapper.toCoordinates(entity.getCoordinatesEmb())
        );
    }

    // Реализует POST
    // Принимает данные от HeroCreateRequest
    // Именно те, которые присылает клиент
    // Создаёт нового героя и сохраняет в БД
    public HeroResponse create(HeroCreateRequest request) {
        // DTO
        // Создаём базового героя
        Hero hero = new Hero();
        // Затем заменяем часть этих данных информацией, присланной клиентом
        hero.setMaxHp(request.maxHp());
        hero.setHp(request.hp());
        hero.setName(request.name());
        hero.setMoney(request.money());
        hero.setHeal(request.heal());
        hero.setCoordinates(request.coordinates());

        // От домена (игровой модели) к сущности через маппер
        HeroEntity entity = HeroMapper.toEntity(hero);

        // Сохранить в виде JPA-сущности
        HeroEntity saved = heroRepository.save(entity);

        // От сущности к DTO
        return toResponse(saved);
    }

    // Реализует READ
    // Java -> JSON
    // Читает всех героев из БД и возвращает JSON список
    public List<HeroResponse> findAll() {
        return heroRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}