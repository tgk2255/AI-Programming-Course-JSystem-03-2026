-- liquibase formatted sql

-- changeset codex:005-drop-legacy-session
DELETE FROM public.chat_message
WHERE session_id = 'legacy-session';

DELETE FROM public.chat_session
WHERE session_id = 'legacy-session';
