-- liquibase formatted sql

-- changeset codex:004-clean-legacy-test-messages
DELETE FROM public.chat_message
WHERE content IN (
    'czeÅÄ, co robisz konsultant?',
    'Powiedz mi coÅ o kredycie',
    'CzeÅÄ, co porabiasz ?'
);
