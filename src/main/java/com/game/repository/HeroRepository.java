package com.game.repository;

import com.game.model.entity.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Сохраняет и читает данные через JPA
// После него entity переносится в базу данных H2 в таблицу heroes
// И наоборот может достать героя из базы данных и передать в HeroService
// Методы, такие как save() доступны из-за наследования JpaRepository
public interface HeroRepository extends JpaRepository<HeroEntity, Long> {
}