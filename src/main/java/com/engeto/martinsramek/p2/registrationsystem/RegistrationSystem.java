package com.engeto.martinsramek.p2.registrationsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RegistrationSystem implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;

	public RegistrationSystem(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrationSystem.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("select 1");
	}
}
