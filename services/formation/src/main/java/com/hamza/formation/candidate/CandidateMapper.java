package com.hamza.formation.candidate;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.hamza.formation.role.Role.CANDIDATE;

@Service
@RequiredArgsConstructor
public class CandidateMapper {
    private final PasswordEncoder passwordEncoder;

    public CandidateResponse toResponse(Candidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getEmail(),
                candidate.getCin(),
                candidate.getPhotoUrl()
        );
    }

    public Candidate toCandidate(CandidateRequest candidateRequest) {
        return Candidate.builder()
                .firstName(candidateRequest.firstName())
                .lastName(candidateRequest.lastName())
                .email(candidateRequest.email())
                .cin(candidateRequest.cin())
                .password(passwordEncoder.encode(candidateRequest.password()))
                .role(CANDIDATE)
                .photoUrl(null)
                .build();
    }
}
