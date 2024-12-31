package com.hamza.formation.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthRequest(
        @Email(message = "Email should be valid")
        @NotNull(message = "Email should not be null")
        String email,
        @NotNull(message = "Password should not be null")
        String password
) {
}
