package com.hamza.formation.candidate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamza.formation.file.FileStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@Slf4j
public class CandidateController {
    private final CandidateService candidateService;
    private final FileStorageService fileStorageService;
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);


    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates(Authentication authentication) {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CandidateResponse> createCandidate(
            @RequestPart("candidate") String candidateJson,
            @RequestPart("photo") MultipartFile photo) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CandidateRequest candidateRequest = objectMapper.readValue(candidateJson, CandidateRequest.class);
        candidateRequest = new CandidateRequest(
                candidateRequest.firstName(),
                candidateRequest.lastName(),
                candidateRequest.email(),
                candidateRequest.cin(),
                candidateRequest.password(),
                photo
        );
        return ResponseEntity.status(CREATED).body(candidateService.createCandidate(candidateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(
            @PathVariable Long id,
            @RequestPart(value = "candidate", required = false) CandidateRequest candidateRequest,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        candidateRequest = new CandidateRequest(
                candidateRequest.firstName(),
                candidateRequest.lastName(),
                candidateRequest.email(),
                candidateRequest.cin(),
                candidateRequest.password(),
                photo
        );
        return ResponseEntity.ok(candidateService.updateCandidate(id, candidateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@RequestPart("photo") MultipartFile photo) {
        String filename = fileStorageService.saveFile(photo);
        return ResponseEntity.ok("File uploaded: " + filename);
    }
}
