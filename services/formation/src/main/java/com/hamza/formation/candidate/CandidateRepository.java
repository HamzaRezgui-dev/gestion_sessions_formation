package com.hamza.formation.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> findByCin(String cin);
}
