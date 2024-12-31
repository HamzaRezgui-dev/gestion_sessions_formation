package com.hamza.formation.handler;

import com.hamza.formation.exception.CandidateAlreadyExistsException;
import com.hamza.formation.exception.CandidateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CandidateAlreadyExistsException.class)
    public ResponseEntity<String> handleCandidateAlreadyExists(CandidateAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> handleException(CandidateNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
