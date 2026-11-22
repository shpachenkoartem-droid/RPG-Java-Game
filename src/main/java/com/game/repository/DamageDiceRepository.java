package com.game.repository;

import com.game.model.entity.DamageDiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DamageDiceRepository extends JpaRepository<DamageDiceEntity, Long> {
}