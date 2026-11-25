package com.game.model.createrequest;

import com.game.coordinates.Coordinates;

// Это DTO
// То, что клиент присылает серверу при создании героя (JSON-файл)
// Он описывает какие поля клиент имеет право передать
// Клиент создаёт самое нужное - сервер даёт в ответ HeroResponse со всеми полями
// Клиент не создаёт id
public record HeroCreateRequest (
        int maxHp,
        int hp,
        String name,
        int money,
        int heal,
        Coordinates coordinates
) {}
