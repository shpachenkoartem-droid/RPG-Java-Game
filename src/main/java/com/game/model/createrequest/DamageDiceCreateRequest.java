package com.game.model.createrequest;

public record DamageDiceCreateRequest (
        int count,
        int sides,
        int bonus
) {}