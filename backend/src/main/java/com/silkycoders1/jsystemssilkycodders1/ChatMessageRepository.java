package com.silkycoders1.jsystemssilkycodders1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepository {

	private static final RowMapper<ChatMessage> CHAT_MESSAGE_ROW_MAPPER = ChatMessageRepository::mapRow;

	private final JdbcTemplate jdbcTemplate;

	public ChatMessageRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ChatMessage> findAll() {
		return jdbcTemplate.query("""
			SELECT id, role, author, content, created_at
			FROM public.chat_message
			ORDER BY created_at ASC, id ASC
			""", CHAT_MESSAGE_ROW_MAPPER);
	}

	public ChatMessage save(String role, String author, String content) {
		ChatMessage savedMessage = jdbcTemplate.queryForObject("""
			INSERT INTO public.chat_message (role, author, content)
			VALUES (?, ?, ?)
			RETURNING id, role, author, content, created_at
			""", CHAT_MESSAGE_ROW_MAPPER, role, author, content);

		if (savedMessage == null) {
			throw new IllegalStateException("Insert into chat_message returned no row.");
		}

		return savedMessage;
	}

	private static ChatMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ChatMessage(
			rs.getLong("id"),
			rs.getString("role"),
			rs.getString("author"),
			rs.getString("content"),
			rs.getObject("created_at", OffsetDateTime.class)
		);
	}
}
