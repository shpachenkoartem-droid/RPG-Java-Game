package com.game.service;

import com.game.dice.DamageDice;
import com.game.model.createrequest.DamageDiceCreateRequest;
import com.game.model.entity.DamageDiceEntity;
import com.game.model.mapper.DamageDiceMapper;
import com.game.model.response.DamageDiceResponse;
import com.game.repository.DamageDiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageDiceService {
    private final DamageDiceRepository damageDiceRepository;

    public DamageDiceService(DamageDiceRepository damageDiceRepository) {
        this.damageDiceRepository = damageDiceRepository;
    }

    private DamageDiceResponse toResponse(DamageDiceEntity entity) {
        return new DamageDiceResponse(
                entity.getId(),
                entity.getCount(),
                entity.getSides(),
                entity.getBonus()
        );
    }

    public DamageDiceResponse create(DamageDiceCreateRequest request) {
        DamageDice dice = new DamageDice(request.count(), request.sides(), request.bonus());

        DamageDiceEntity entity = DamageDiceMapper.toEntity(dice);

        DamageDiceEntity saved = damageDiceRepository.save(entity);

        return toResponse(saved);
    }

    public List<DamageDiceResponse> findAll() {
        return damageDiceRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}
