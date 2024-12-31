package com.hamza.formation.candidate;

import com.hamza.formation.exception.CandidateAlreadyExistsException;
import com.hamza.formation.exception.CandidateNotFoundException;
import com.hamza.formation.file.FileStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final FileStorageService fileStorageService;

    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(candidateMapper::toResponse)
                .toList();
    }

    public CandidateResponse createCandidate(CandidateRequest candidateRequest) {
        candidateRepository.findByEmail(candidateRequest.email())
                .ifPresent(existing -> {
                    throw new CandidateAlreadyExistsException("A candidate with this email already exists.");
                });

        candidateRepository.findByCin(candidateRequest.cin())
                .ifPresent(existing -> {
                    throw new CandidateAlreadyExistsException("A candidate with this CIN already exists.");
                });
        String photoFilename = null;
        if (candidateRequest.photo() != null && !candidateRequest.photo().isEmpty()) {
            photoFilename = fileStorageService.saveFile(candidateRequest.photo());
        }

        Candidate candidate = candidateMapper.toCandidate(candidateRequest);
        candidate.setPhotoUrl(photoFilename);
        return candidateMapper.toResponse(candidateRepository.save(candidate));

    }

    public CandidateResponse updateCandidate(Long id,CandidateRequest candidateRequest) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with id " + id + " not found."));

        candidate.setEmail(candidateRequest.email());
        candidate.setFirstName(candidateRequest.firstName());
        candidate.setLastName(candidateRequest.lastName());
        candidate.setCin(candidateRequest.cin());
        if (candidateRequest.photo() != null && !candidateRequest.photo().isEmpty()) {
            String oldPhotoUrl = candidate.getPhotoUrl();

            // Save new file and update the photo URL
            String newPhotoFilename = fileStorageService.saveFile(candidateRequest.photo());
            candidate.setPhotoUrl(newPhotoFilename);

            // Optionally delete the old photo
            if (oldPhotoUrl != null) {
                fileStorageService.deleteFile(oldPhotoUrl); // You need to implement deleteFile in FileStorageService
            }
        }
        var updatedCandidate = candidateRepository.save(candidate);
        return candidateMapper.toResponse(updatedCandidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateRepository::delete,
                        () -> {
                            throw new CandidateNotFoundException("Candidate with id " + id + " not found.");
                        });
    }
}
