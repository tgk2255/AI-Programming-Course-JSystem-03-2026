-- liquibase formatted sql

-- changeset codex:003-chat-session-and-cleanup
CREATE TABLE IF NOT EXISTS public.chat_session (
    session_id VARCHAR(64) PRIMARY KEY,
    title VARCHAR(160) NOT NULL DEFAULT 'Rozmowa kredytowa',
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO public.chat_session (session_id, title)
VALUES ('legacy-session', 'Stara rozmowa')
ON CONFLICT (session_id) DO NOTHING;

ALTER TABLE public.chat_message
    ADD COLUMN IF NOT EXISTS session_id VARCHAR(64);

UPDATE public.chat_message
SET session_id = 'legacy-session'
WHERE session_id IS NULL;

ALTER TABLE public.chat_message
    ALTER COLUMN session_id SET NOT NULL;

ALTER TABLE public.chat_message
    ADD CONSTRAINT fk_chat_message_session
    FOREIGN KEY (session_id) REFERENCES public.chat_session (session_id);

CREATE INDEX IF NOT EXISTS idx_chat_message_session_created_at
    ON public.chat_message (session_id, created_at ASC, id ASC);

DELETE FROM public.chat_message
WHERE content IN (
    'Test wiadomosci zapisanej do PostgreSQL',
    'czeÅÄ, co robisz konsultant?',
    'Powiedz mi coÅ o kredycie'
);
