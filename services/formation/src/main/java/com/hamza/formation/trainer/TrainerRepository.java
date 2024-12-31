package com.hamza.formation.trainer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByEmail(String email);
    Optional<Trainer> findByCin(String cin);
}
