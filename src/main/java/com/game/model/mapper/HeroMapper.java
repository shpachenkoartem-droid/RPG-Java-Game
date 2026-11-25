package com.game.model.mapper;

import com.game.coordinates.Coordinates;
import com.game.coordinates.CoordinatesEmbeddable;
import com.game.model.entity.HeroEntity;
import com.game.model.heroes.Hero;

// Маппер - "переводчик" между двумя моделями
public class HeroMapper {

    // Берёт Hero и создаёт из него HeroEntity, копируя все поля.
    // ID потом генерирует база данных
    // Преобразует игровой объект в объект, пригодный для сохранения в БД
    public static HeroEntity toEntity(Hero hero) {
        // Создаёт новую сущность и заполняет её информацией из домена
        HeroEntity e = new HeroEntity();
        e.setName(hero.getName());
        e.setMaxHp(hero.getMaxHp());
        e.setHp(hero.getHp());
        e.setMoney(hero.getMoney());
        e.setHeal(hero.getHeal());

        // Берём объект как часть доменной модели и преобразуем в игровой
        // Получаем координаты, хранящиеся в Hero
        // В e надо передать координаты в виде столцов БД, то есть CoordinatesEmbeddable
        Coordinates coordinates = hero.getCoordinates();
        CoordinatesEmbeddable coordinatesEmbeddable = new CoordinatesEmbeddable();
        coordinatesEmbeddable.setX(coordinates.getX());
        coordinatesEmbeddable.setY(coordinates.getY());

        e.setCoordinatesEmb(coordinatesEmbeddable);

        return e;
    }

    // Из HeroEntity создаёт Hero
    // Преобразует объект из БД в игровой объект
    public static Hero toDomain(HeroEntity e) {
        // Создаёт новый домен и заполняет его информацией из сущности
        Hero hero = new Hero();
        hero.setName(e.getName());
        hero.setMaxHp(e.getMaxHp());
        hero.setHp(e.getHp());
        hero.setMoney(e.getMoney());
        hero.setHeal(e.getHeal());

        // Берём объект как часть БД и преобразуем в игровой
        CoordinatesEmbeddable embedded = e.getCoordinatesEmb();
        Coordinates coordinates = new Coordinates(embedded.getX(), embedded.getY());

        hero.setCoordinates(coordinates);

        return hero;
    }
}
