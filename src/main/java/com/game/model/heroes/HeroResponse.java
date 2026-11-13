package com.game.model.heroes;

// Это то, что сервер отправляет клиенту в ответ на запрос
// Это DTO
// Он описывает какие поля героя клиент имеет право видеть
public record HeroResponse (
        Long id,
        int maxHp,
        int hp,
        String name,
        int money,
        int heal
) {}
