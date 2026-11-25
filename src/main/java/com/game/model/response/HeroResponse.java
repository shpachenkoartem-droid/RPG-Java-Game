package com.game.model.response;

import com.game.coordinates.Coordinates;

// Это то, что сервер отправляет клиенту в ответ на запрос
// Это DTO
// Он описывает какие поля героя клиент имеет право видеть
public record HeroResponse (
        Long id,
        int maxHp,
        int hp,
        String name,
        int money,
        int heal,
        Coordinates coordinates
) {}
