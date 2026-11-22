package com.game.model.response;

public record DamageDiceResponse (
    Long id,
    int count,
    int sides,
    int bonus
) {}
