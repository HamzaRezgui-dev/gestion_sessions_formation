package com.hamza.formation.admin;

import com.hamza.formation.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByRole(Role role);
    Optional<Admin> findByEmail(String email);
}
