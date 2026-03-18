-- liquibase formatted sql

-- changeset codex:001-create-chat-message
CREATE TABLE IF NOT EXISTS public.chat_message (
    id BIGSERIAL PRIMARY KEY,
    author VARCHAR(80) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_chat_message_created_at
    ON public.chat_message (created_at DESC, id DESC);
