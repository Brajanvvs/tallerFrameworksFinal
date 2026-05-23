package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.SuperAdministrador;
import com.example.demo.repository.SuperAdministradorRepository;

@SpringBootApplication
public class CrudSpringbootPgReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringbootPgReactApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(SuperAdministradorRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				SuperAdministrador admin = new SuperAdministrador();
				admin.setName("Admin");
				admin.setEmail("admin@email.com");
				admin.setPassword("1234");
				repo.save(admin);
				System.out.println("✅ Usuario admin creado: admin@email.com / 1234");
			}
		};
	}
}
