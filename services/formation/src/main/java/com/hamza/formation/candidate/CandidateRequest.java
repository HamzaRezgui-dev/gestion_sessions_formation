package com.hamza.formation.candidate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CandidateRequest(
    @NotNull(message = "First name is required")
    String firstName,
    @NotNull(message = "Last name is required")
    String lastName,
    @NotNull(message = "CIN is required")
    String cin,
    @NotNull(message = "Email is required")
    String email,
    @NotNull(message = "Password is required")
    @Min(value = 8, message = "Password must be at least 8 characters long")
    String password,
    MultipartFile photo
) {
}
