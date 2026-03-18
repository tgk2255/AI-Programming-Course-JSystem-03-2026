package com.silkycoders1.jsystemssilkycodders1;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerProfileRepository {

	private final JdbcTemplate jdbcTemplate;

	public CustomerProfileRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Optional<CustomerProfile> findByIdentity(String firstName, String lastName, String pesel) {
		return jdbcTemplate.query("""
			SELECT id, first_name, last_name, pesel, monthly_income, currency
			FROM public.customer_profile
			WHERE LOWER(first_name) = LOWER(?)
			  AND LOWER(last_name) = LOWER(?)
			  AND pesel = ?
			""", (rs, rowNum) -> new CustomerProfile(
			rs.getLong("id"),
			rs.getString("first_name"),
			rs.getString("last_name"),
			rs.getString("pesel"),
			rs.getBigDecimal("monthly_income"),
			rs.getString("currency")
		), firstName, lastName, pesel).stream().findFirst();
	}
}
