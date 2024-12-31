package com.hamza.formation;

import com.hamza.formation.admin.Admin;
import com.hamza.formation.admin.AdminRepository;
import com.hamza.formation.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class FormationApplication {
	private final PasswordEncoder passwordEncoder;
	private final AdminRepository AdminRepository;

	public static void main(String[] args) {
		SpringApplication.run(FormationApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AdminRepository adminRepository,
						  PasswordEncoder passwordEncoder) {
		return args -> {
			if (adminRepository.findByRole(Role.ADMIN).isPresent())
				return;
			Admin admin = new Admin("admin@example.com", passwordEncoder.encode("password"));
			AdminRepository.save(admin);
		};
	}
}
