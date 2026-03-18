package com.silkycoders1.jsystemssilkycodders1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JSystemsSilkyCodders1Application {

	private static final Logger log = LoggerFactory.getLogger(JSystemsSilkyCodders1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(JSystemsSilkyCodders1Application.class, args);
	}

	@Bean
	CommandLineRunner verifyDatabaseConnection(JdbcTemplate jdbcTemplate) {
		return args -> {
			Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
			log.info("PostgreSQL connection check passed, SELECT 1 returned: {}", result);
		};
	}

}
