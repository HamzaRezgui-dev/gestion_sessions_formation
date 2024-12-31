package com.hamza.formation.user;

import com.hamza.formation.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String cin;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String photoUrl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + role.name());
    }

    @Override
    public String getUsername() {
        return email; // Spring Security uses this for authentication
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize based on your needs
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize based on your needs
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize based on your needs
    }

    @Override
    public boolean isEnabled() {
        return true; // Add a field if you want to manage enabled/disabled accounts
    }

}
