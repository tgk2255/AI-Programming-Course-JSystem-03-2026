package com.silkycoders1.jsystemssilkycodders1;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/db")
public class DbHealthController {

	private final JdbcTemplate jdbcTemplate;

	public DbHealthController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping("/health")
	public ResponseEntity<Map<String, Object>> health() {
		Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

		return ResponseEntity.ok(Map.of(
			"status", "ok",
			"database", "postgresql",
			"query", "SELECT 1",
			"result", result,
			"checkedAt", OffsetDateTime.now().toString()
		));
	}
}
