package com.hamza.formation.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(String message) {
        super(message);
    }
}
