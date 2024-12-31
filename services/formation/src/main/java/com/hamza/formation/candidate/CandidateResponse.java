package com.hamza.formation.candidate;

public record CandidateResponse(
    Long id,
    String firstName,
    String lastName,
    String cin,
    String email,
    String photo
) {
}
