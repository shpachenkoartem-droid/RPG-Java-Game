package com.game.repository;

import com.game.model.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository - это единственный способ,
// Которым Java-код может сказать базе данных «сохрани» или «найди»
// Он выполняет SQL запросы с указанной БД (EquipmentEntity)
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
}