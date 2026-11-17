package com.game.model.createrequest;

// Это DTO
// То, что клиент присылает серверу при создании героя
// Он описывает какие поля клиент имеет право передать
// Клиент создаёт самое нужное - сервер даёт в ответ HeroResponse со всеми полями
// В этом примере клиент не создаёт id
public record HeroCreateRequest (
        int maxHp,
        int hp,
        String name,
        int money,
        int heal
) {}
