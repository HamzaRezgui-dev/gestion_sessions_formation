package com.hamza.formation.user;

import com.hamza.formation.admin.Admin;
import com.hamza.formation.admin.AdminRepository;
import com.hamza.formation.candidate.Candidate;
import com.hamza.formation.candidate.CandidateRepository;
import com.hamza.formation.trainer.Trainer;
import com.hamza.formation.trainer.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CandidateRepository candidateRepository;
    private final TrainerRepository trainerRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Candidate candidate = candidateRepository.findByEmail(email)
                .orElse(null);

        if (candidate != null) {
            return candidate;
        }

        Trainer trainer = trainerRepository.findByEmail(email)
                .orElse(null);

        if (trainer != null) {
            return trainer;
        }

        Admin admin = adminRepository.findByEmail(email)
                .orElse(null);

        if (admin != null) {
            return admin;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
