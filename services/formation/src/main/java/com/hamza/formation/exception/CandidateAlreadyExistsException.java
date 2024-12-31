package com.hamza.formation.exception;

public class CandidateAlreadyExistsException extends RuntimeException {
    public CandidateAlreadyExistsException(String message) {
        super(message);
    }
}
